package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.IMessageApi;
import spark.course.entity.bo.MessageBO;
import spark.course.error.BusinessException;
import spark.course.service.MessageService;
import spark.course.util.JsonUtil;

/**
 * @ClassName MessageController
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:48 AM
 * @Version 1.0
 **/
@RestController
public class MessageController implements IMessageApi {
    @Autowired
    MessageService messageService;

    @Override
    public String selectByMessageId(@PathVariable("messageId") Integer messageId) {
        return JsonUtil.convertToJson(messageService.selectByMessageId(messageId));
    }

    @Override
    public String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                         @PathVariable("start") Integer start,
                                         @PathVariable("size") Integer size) {
        return JsonUtil.convertToJson(messageService.
                selectByChooseCourseId(chooseCourseId, start, size));
    }

    @Override
    public String insert(@RequestBody MessageBO messageBO) {
        return JsonUtil.convertToJson(messageService.insert(messageBO));
    }

    @Override
    public void delete(@PathVariable("messageId") Integer messageId) {
        messageService.delete(messageId);
    }

    @Override
    public String update(@RequestBody MessageBO messageBO) throws BusinessException {
        return JsonUtil.convertToJson(messageService.updata(messageBO));
    }
}
