package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.ISignApi;
import spark.course.entity.bo.SignBO;
import spark.course.error.BusinessException;
import spark.course.service.SignService;
import spark.course.util.JsonUtil;
import spark.course.validator.ValidationExceptionHandler;

import javax.validation.Valid;

/**
 * @ClassName SignController
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 11:23 AM
 * @Version 1.0
 **/
@RestController
public class SignController extends ValidationExceptionHandler implements ISignApi {
    @Autowired
    SignService signService;

    @Override
    public String selectBySignId(@PathVariable("signId") Integer signId) throws BusinessException {
        return JsonUtil.convertToJson(signService.selectBySignId(signId));
    }

    @Override
    public String selectByChooseCoureId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                        @PathVariable("start") Integer start,
                                        @PathVariable("size") Integer size) throws BusinessException {
        return JsonUtil.convertToJson(signService.
                selectByChooseCourseId(chooseCourseId, start, size));
    }

    @Override
    public String insert(@RequestBody @Valid SignBO signBO) throws BusinessException {
        return JsonUtil.convertToJson(signService.insert(signBO));
    }

    @Override
    public void delete(@PathVariable("signId") Integer signId)throws BusinessException {
        signService.delete(signId);
    }

    @Override
    public String update(@RequestBody SignBO signBO) throws BusinessException {
        return JsonUtil.convertToJson(signService.update(signBO));
    }
}
