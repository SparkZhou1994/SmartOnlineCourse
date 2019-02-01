package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String selectByDiscussId(@PathVariable("discussId") Integer discussId,
                                    @PathVariable("start") Integer start,
                                    @PathVariable("size") Integer size) {
        return JsonUtil.convertToJson(discussContentService.selectByDiscussId(discussId, start, size));
    }

    @Override
    public String selectByPrimaryKey(@PathVariable("discussContentId") Integer discussContentId) {
        return JsonUtil.convertToJson(discussContentService.selectByPrimaryKey(discussContentId));
    }

    @Override
    public String insert(@RequestBody DiscussContentBO discussContentBO) {
        return JsonUtil.convertToJson(discussContentService.insert(discussContentBO));
    }

    @Override
    public void delete(@PathVariable("discussContentId") Integer discussContentId) {
        discussContentService.delete(discussContentId);
    }

    @Override
    public String update(@RequestBody DiscussContentBO discussContentBO) throws BusinessException {
        return JsonUtil.convertToJson(discussContentService.update(discussContentBO));
    }
}
