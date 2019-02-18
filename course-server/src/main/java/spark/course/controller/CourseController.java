package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.ICourseApi;
import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;
import spark.course.service.CourseService;
import spark.course.util.JsonUtil;
import spark.course.validator.ValidationExceptionHandler;

import javax.validation.Valid;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 11:49 AM
 * @Version 1.0
 **/
@RestController
public class CourseController extends ValidationExceptionHandler implements ICourseApi {
    @Autowired
    CourseService courseService;

    @Override
    public String selectByCourseId(@PathVariable(value = "courseId") Integer courseId) throws BusinessException {
        return JsonUtil.convertToJson(courseService.selectByCourseId(courseId));
    }

    @Override
    public String selectByCourseName(@PathVariable(value = "courseName") String courseName,
                                     @PathVariable(value = "start") Integer start,
                                     @PathVariable(value = "size") Integer size) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setCourseName(courseName);
        courseBO.setStart(start);
        courseBO.setSize(size);
        return JsonUtil.convertToJson(courseService.selectCourseByCourseName(courseBO));
    }

    @Override
    public String selectSortByScore(@PathVariable(value = "start") Integer start,
                                    @PathVariable(value = "size") Integer size) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setStart(start);
        courseBO.setSize(size);
        return JsonUtil.convertToJson(courseService.selectCourseSortByScore(courseBO));
    }

    @Override
    public String insert(@RequestBody @Valid  CourseBO courseBO) throws BusinessException {
        return JsonUtil.convertToJson(courseService.insert(courseBO));
    }

    @Override
    public void deleteCourse(@PathVariable(value = "courseId") Integer courseId) throws BusinessException {
        courseService.deleteByCourseId(courseId);
    }

    @Override
    public String updataCourseByCourseId(@RequestBody CourseBO courseBO) throws BusinessException {
        return JsonUtil.convertToJson(courseService.updateByCourseId(courseBO));
    }
}
