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
import spark.course.validator.ValidationExceptionHandler;

import javax.validation.Valid;

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
    public String selectByChooseCourseId(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) throws BusinessException {
        return JsonUtil.convertToJson(chooseCourseService.selectByChooseCourseId(chooseCourseId));
    }

    @Override
    public String selectChooseCourseByUserIdAndCourseId(@PathVariable(value = "userId") Integer userId,
                                                          @PathVariable(value = "courseId") Integer courseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setUserId(userId);
        courseBO.setCourseId(courseId);
        return JsonUtil.convertToJson(chooseCourseService.selectChooseCourseByUserIdAndCourseId(courseBO));
    }

    @Override
    public String insert(@RequestBody CourseBO courseBO) throws BusinessException {
        return JsonUtil.convertToJson(chooseCourseService.insert(courseBO));
    }

    @Override
    public void deleteChooseCourse(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) throws BusinessException {
        chooseCourseService.deleteByChooseCourseId(chooseCourseId);
    }

    @Override
    public String updateByChooseCourseId(@RequestBody CourseBO courseBO) throws BusinessException {
        return JsonUtil.convertToJson(chooseCourseService.updateByChooseCourseId(courseBO));
    }

    @Override
    public String selectByCourseId(@PathVariable(value = "courseId") Integer courseId) throws BusinessException {
        return JsonUtil.convertToJson(chooseCourseService.selectChooseCourseByCourseId(courseId));
    }

    @Override
    public String selectByUserId(@PathVariable(value = "userId") Integer userId) throws BusinessException {
        return JsonUtil.convertToJson(chooseCourseService.selectChooseCourseByUserId(userId));
    }
}
