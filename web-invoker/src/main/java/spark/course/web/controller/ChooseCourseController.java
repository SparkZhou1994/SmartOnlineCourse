package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignCourseApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.CourseVO;
import spark.course.error.BusinessException;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChooseCourseController
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 5:22 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/chooseCourse")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ChooseCourseController extends BaseController {
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignCourseApi courseService;
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{chooseCourseId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByChooseCourseId(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) throws BusinessException {
        return convertFromChooseCourseBOJson(chooseCourseService.
                selectByChooseCourseId(chooseCourseId));
    }

    @GetMapping(value = "/{userId:\\d+}/{courseId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectChooseCourseByUserIdAndCourseId(@PathVariable(value = "userId") Integer userId,
                                                   @PathVariable(value = "courseId") Integer courseId) throws BusinessException {
        return convertFromChooseCourseBOJson(chooseCourseService.
                selectChooseCourseByUserIdAndCourseId(userId,courseId));
    }

    @GetMapping(value = "/courseId/{courseId}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByCourseId(@PathVariable(value = "courseId") Integer courseId) throws BusinessException {
        return convertFromChooseCouseBOJsonList(chooseCourseService.selectByCourseId(courseId));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String insert(@RequestBody CourseVO courseVO) throws BusinessException {
        return convertFromChooseCourseBOJson(chooseCourseService.insert(convertToBO(courseVO)));
    }

    @DeleteMapping(value = "/{chooseCourseId}")
    public void deleteChooseCourse(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) throws BusinessException {
        chooseCourseService.deleteChooseCourse(chooseCourseId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String updateByChooseCourseId(@RequestBody CourseVO courseVO) throws BusinessException {
        return convertFromChooseCourseBOJson(chooseCourseService.
                updateByChooseCourseId(convertToBO(courseVO)));
    }

    private CourseVO convertFromBO(CourseBO courseBO) {
        if (courseBO == null) {
            return null;
        }
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(courseBO, courseVO);
        return courseVO;
    }

    private CourseBO convertToBO(CourseVO courseVO) {
        if (courseVO == null) {
            return null;
        }
        CourseBO courseBO = new CourseBO();
        BeanUtils.copyProperties(courseVO, courseBO);
        return courseBO;
    }

    private String convertFromChooseCourseBOJson(String chooseCourseBOJson) throws BusinessException {
        CourseVO courseVOTemp = convertFromBO(JsonUtil.json2Bean(chooseCourseBOJson,CourseBO.class));
        CourseVO courseVO = convertFromBO(JsonUtil.json2Bean(courseService.
                selectByCourseId(courseVOTemp.getCourseId()),CourseBO.class));
        courseVO.setOwnerUserId(courseVO.getUserId());
        courseVO.setUserId(courseVOTemp.getUserId());
        courseVO.setScore(courseVOTemp.getScore());
        courseVO.setVersionChooseCourse(courseVOTemp.getVersionChooseCourse());
        UserBO userBO = JsonUtil.json2Bean(userService.
                selectByUserId(courseVO.getOwnerUserId()),UserBO.class);
        courseVO.setOwnerUsername(userBO.getUsername());
        return JsonUtil.convertToJson(courseVO);
    }

    private String convertFromChooseCouseBOJsonList(String chooseCourseBOJsonList) throws BusinessException {
        List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseBOJsonList, CourseBO.class);
        List<CourseVO> courseVOList = new ArrayList<CourseVO>();
        for (CourseBO courseBO:courseBOList) {
            courseVOList.add(JsonUtil.json2Bean(convertFromChooseCourseBOJson(JsonUtil.convertToJson(courseBO)),CourseVO.class));
        }
        return JsonUtil.convertToJson(courseVOList);
    }
}
