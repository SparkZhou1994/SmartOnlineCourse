package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignChooseCourseApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.vo.CourseVO;
import spark.course.error.BusinessException;
import spark.course.util.JsonUtil;

/**
 * @ClassName ChooseCourseController
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 5:22 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/chooseCourse")
public class ChooseCourseController extends BaseController {
    @Autowired
    FeignChooseCourseApi chooseCourseService;

    @GetMapping(value = "/{chooseCourseId:\\d+}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(chooseCourseService.
                selectByChooseCourseId(chooseCourseId),CourseBO.class)));
    }

    @GetMapping(value = "/{userId:\\d+}/{courseId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectChooseCourseIdByUserIdAndCourseId(@PathVariable(value = "userId") Integer userId,
                                                   @PathVariable(value = "courseId") Integer courseId) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(chooseCourseService.
                selectChooseCourseIdByUserIdAndCourseId(userId,courseId),CourseBO.class)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseVO courseVO) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(chooseCourseService.
                insert(convertToBO(courseVO)),CourseBO.class)));
    }

    @DeleteMapping(value = "/{chooseCourseId}")
    void deleteChooseCourse(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) {
        chooseCourseService.deleteChooseCourse(chooseCourseId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String updateByChooseCourseId(@RequestBody CourseVO courseVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(chooseCourseService.
                updateByChooseCourseId(convertToBO(courseVO)),CourseBO.class)));
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
}
