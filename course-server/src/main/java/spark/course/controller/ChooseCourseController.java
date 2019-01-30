package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.IChooseCourseApi;
import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;
import spark.course.service.ChooseCourseService;
import spark.course.util.JsonUtil;

/**
 * @ClassName ChooseCourseController
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 4:55 PM
 * @Version 1.0
 **/
@RestController
public class ChooseCourseController implements IChooseCourseApi {
    @Autowired
    ChooseCourseService chooseCourseService;
    @Override
    public String selectByChooseCourseId(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) {
        return JsonUtil.convertToJson(chooseCourseService.selectByChooseCourseId(chooseCourseId));
    }

    @Override
    public String selectChooseCourseIdByUserIdAndCourseId(@PathVariable(value = "userId") Integer userId,
                                                          @PathVariable(value = "courseId") Integer courseId) {
        CourseBO courseBO = new CourseBO();
        courseBO.setUserId(userId);
        courseBO.setCourseId(courseId);
        return JsonUtil.convertToJson(chooseCourseService.selectChooseCourseIdByUserIdAndCourseId(courseBO));
    }

    @Override
    public String insert(@RequestBody CourseBO courseBO) {
        return JsonUtil.convertToJson(chooseCourseService.insert(courseBO));
    }

    @Override
    public void deleteChooseCourse(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) {
        chooseCourseService.deleteByChooseCourseId(chooseCourseId);
    }

    @Override
    public String updateByChooseCourseId(@RequestBody CourseBO courseBO) throws BusinessException {
        return JsonUtil.convertToJson(chooseCourseService.updateByChooseCourseId(courseBO));
    }
}