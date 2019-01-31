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

/**
 * @ClassName DiscussController
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:47 PM
 * @Version 1.0
 **/
@RestController
public class DiscussController implements IDiscussApi {
    @Autowired
    DiscussService discussService;
    @Override
    public String selectByChooseCourseId(@RequestBody DiscussBO discussBO) {
        return JsonUtil.convertToJson(discussService.selectByChooseCourseId(discussBO));
    }

    @Override
    public String selectByDiscussId(@PathVariable("discussId") Integer discussId) {
        return JsonUtil.convertToJson(discussService.selectByDiscussId(discussId));
    }

    @Override
    public String insert(DiscussBO discussBO) {
        return JsonUtil.convertToJson(discussService.insert(discussBO));
    }

    @Override
    public void delete(Integer discussId) {
        discussService.delete(discussId);
    }

    @Override
    public String update(DiscussBO discussBO) throws BusinessException {
        return JsonUtil.convertToJson(discussService.update(discussBO));
    }
}
