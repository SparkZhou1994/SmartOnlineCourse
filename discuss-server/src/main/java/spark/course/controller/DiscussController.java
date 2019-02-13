package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.IDiscussApi;
import spark.course.entity.bo.DiscussBO;
import spark.course.error.BusinessException;
import spark.course.service.DiscussService;
import spark.course.util.JsonUtil;
import spark.course.validator.ValidationExceptionHandler;

import javax.validation.Valid;

/**
 * @ClassName DiscussController
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:47 PM
 * @Version 1.0
 **/
@RestController
public class DiscussController extends ValidationExceptionHandler implements IDiscussApi {
    @Autowired
    DiscussService discussService;
    @Override
    public String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId) {
        return JsonUtil.convertToJson(discussService.selectByChooseCourseId(chooseCourseId));
    }

    @Override
    public String selectByDiscussId(@PathVariable("discussId") Integer discussId) {
        return JsonUtil.convertToJson(discussService.selectByDiscussId(discussId));
    }

    @Override
    public String insert(@RequestBody @Valid DiscussBO discussBO) throws BusinessException {
        return JsonUtil.convertToJson(discussService.insert(discussBO));
    }

    @Override
    public void delete(@PathVariable("discussId") Integer discussId) {
        discussService.delete(discussId);
    }

    @Override
    public String update(@RequestBody DiscussBO discussBO) throws BusinessException {
        return JsonUtil.convertToJson(discussService.update(discussBO));
    }
}
