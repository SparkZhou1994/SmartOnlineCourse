package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignCourseApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.CourseVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName CourseController
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 12:24 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/course")
public class CourseController extends BaseController {
    @Autowired
    FeignCourseApi courseService;
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{courseId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByCourseId(@PathVariable(value = "courseId") Integer courseId) {
        CourseVO courseVO = convertFromBO(JsonUtil.json2Bean(
                courseService.selectByCourseId(courseId), CourseBO.class));
        UserBO userBO = JsonUtil.json2Bean(userService.
                selectByUserId(courseVO.getOwnerUserId()),UserBO.class);
        courseVO.setOwnerUsername(userBO.getUsername());
        return JsonUtil.convertToJson(courseVO);
    }

    @GetMapping(value = "/{courseName}/{start:\\d+}/{size:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByCourseName(@PathVariable(value = "courseName") String courseName,
                              @PathVariable(value = "start") Integer start,
                              @PathVariable(value = "size") Integer size) {
        return convertFromCourseBOListJsonToCourseVOListJson(courseService.
                selectByCourseName(courseName, start, size));
    }

    @GetMapping(value = "/{start:\\d+}/{size:\\d+}")
    public String selectSortByScore(@PathVariable(value = "start") Integer start,
                             @PathVariable(value = "size") Integer size) {
        return convertFromCourseBOListJsonToCourseVOListJson(courseService.
                selectSortByScore(start, size));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String insert(@RequestBody CourseVO courseVO) throws BusinessException{
        String result = courseService.insert(convertToBO(courseVO));
        if (result.contains("true")) {
            throw new BusinessException(new ClassBusinessError(result));
        }
        courseVO = convertFromBO(JsonUtil.json2Bean(result,CourseBO.class));
        courseVO.setOwnerUsername(JsonUtil.json2Bean(userService.
                selectByUserId(courseVO.getOwnerUserId()), UserBO.class).getUsername());
        return JsonUtil.convertToJson(courseVO);
    }

    @DeleteMapping(value = "/{courseId:\\d+}")
    public void deleteCourse(@PathVariable(value = "courseId") Integer courseId) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String updataCourseByCourseId(@RequestBody CourseVO courseVO) throws BusinessException {
        courseVO = convertFromBO(JsonUtil.json2Bean(courseService.
                updataCourseByCourseId(convertToBO(courseVO)),CourseBO.class));
        courseVO.setOwnerUsername(JsonUtil.json2Bean(userService.
                selectByUserId(courseVO.getOwnerUserId()), UserBO.class).getUsername());
        return JsonUtil.convertToJson(courseVO);
    }

    private CourseVO convertFromBO(CourseBO courseBO) {
        if (courseBO == null) {
            return null;
        }
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(courseBO, courseVO);
        if (courseBO.getUserId() != null) {
            courseVO.setOwnerUserId(courseBO.getUserId());
            courseVO.setUserId(null);
        }
        return courseVO;
    }

    private CourseBO convertToBO(CourseVO courseVO) {
        if (courseVO == null) {
            return null;
        }
        CourseBO courseBO = new CourseBO();
        BeanUtils.copyProperties(courseVO, courseBO);
        if (courseVO.getOwnerUserId() != null) {
            courseBO.setUserId(courseVO.getOwnerUserId());
        }
        return courseBO;
    }

    private String convertFromCourseBOListJsonToCourseVOListJson(String courseBOListJson) {
        List<CourseVO> courseVOList = new ArrayList<CourseVO>();
        List<CourseBO> courseBOList = JsonUtil.json2List(courseBOListJson, CourseBO.class);
        for (CourseBO courseBO : courseBOList) {
            CourseVO courseVO = convertFromBO(courseBO);
            UserBO userBO = JsonUtil.json2Bean(userService.selectByUserId(courseBO.getUserId()),UserBO.class);
            courseVO.setOwnerUsername(userBO.getUsername());
            courseVOList.add(courseVO);
        }
        return JsonUtil.convertToJson(courseVOList);
    }
}
