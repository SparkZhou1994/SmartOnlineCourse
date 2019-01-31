package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.IDiscussContentApi;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.error.BusinessException;
import spark.course.service.DiscussContentService;
import spark.course.util.JsonUtil;

/**
 * @ClassName DiscussContentController
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 3:55 PM
 * @Version 1.0
 **/
@RestController
public class DiscussContentController implements IDiscussContentApi {
    @Autowired
    DiscussContentService discussContentService;
    @Override
    public String selectByDiscussId(DiscussContentBO discussContentBO) {
        return JsonUtil.convertToJson(discussContentService.selectByDiscussId(discussContentBO));
    }

    @Override
    public String selectByPrimaryKey(Integer discussContentId) {
        return JsonUtil.convertToJson(discussContentService.selectByPrimaryKey(discussContentId));
    }

    @Override
    public String insert(DiscussContentBO discussContentBO) {
        return JsonUtil.convertToJson(discussContentService.insert(discussContentBO));
    }

    @Override
    public void delete(Integer discussContentId) {
        discussContentService.delete(discussContentId);
    }

    @Override
    public String update(DiscussContentBO discussContentBO) throws BusinessException {
        return JsonUtil.convertToJson(discussContentService.update(discussContentBO));
    }
}
