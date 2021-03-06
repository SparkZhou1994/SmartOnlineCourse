package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.ICourseWareApi;
import spark.course.entity.bo.CourseWareBO;
import spark.course.error.BusinessException;
import spark.course.service.CourseWareService;
import spark.course.util.JsonUtil;
import spark.course.validator.ValidationExceptionHandler;

import javax.validation.Valid;

/**
 * @ClassName CourseWareController
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 9:59 AM
 * @Version 1.0
 **/
@RestController
public class CourseWareController extends ValidationExceptionHandler implements ICourseWareApi {
    @Autowired
    CourseWareService courseWareService;

    @Override
    public String selectByCourseWareId(@PathVariable("courseWareId") Integer courseWareId) throws BusinessException {
        return JsonUtil.convertToJson(courseWareService.selectByCourseWareId(courseWareId));
    }

    @Override
    public String selectByCourseId(@PathVariable("courseId") Integer courseId,
                                   @PathVariable("start") Integer start,
                                   @PathVariable("size") Integer size) throws BusinessException {
        return JsonUtil.convertToJson(courseWareService.selectByCourseId(courseId, start, size));
    }

    @Override
    public String insert(@RequestBody @Valid CourseWareBO courseWareBO) throws BusinessException {
        return JsonUtil.convertToJson(courseWareService.insert(courseWareBO));
    }

    @Override
    public void delete(@PathVariable("courseWareId") Integer courseWareId)throws BusinessException {
        courseWareService.delete(courseWareId);
    }

    @Override
    public String update(@RequestBody CourseWareBO courseWareBO) throws BusinessException {
        return JsonUtil.convertToJson(courseWareService.update(courseWareBO));
    }
}
