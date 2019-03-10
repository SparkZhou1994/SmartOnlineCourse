package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignMessageApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseWareBO;
import spark.course.entity.bo.MessageBO;
import spark.course.entity.vo.MessageVO;
import spark.course.error.BusinessException;
import spark.course.util.DateUtil;
import spark.course.util.JsonUtil;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MessageController
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:55 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/message")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class MessageController extends BaseController {
    @Autowired
    FeignMessageApi messageService;

    @GetMapping(value = "/{messageId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByMessageId(@PathVariable("messageId") Integer messageId) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(messageService.
                selectByMessageId(messageId), MessageBO.class)));
    }

    @GetMapping(value = "/{chooseCourseId}/{start}/{size}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                  @PathVariable("start") Integer start,
                                  @PathVariable("size") Integer size) throws BusinessException {
        String messageString = messageService.
                selectByChooseCourseId(chooseCourseId, start, size);
        List<MessageVO> messageVOList = convertFromBOList(messageString);
        return JsonUtil.convertToJson(messageVOList);
    }

    @DeleteMapping(value = "/{messageId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("messageId") Integer messageId) throws BusinessException {
        messageService.delete(messageId);
    }

    private MessageVO convertFromBO(MessageBO messageBO) {
        MessageVO messageVO = new MessageVO();
        BeanUtils.copyProperties(messageBO, messageVO);
        messageVO.setPublishData(DateUtil.convertFromLocalDate(messageBO.getPublishData()));
        return messageVO;
    }

    private MessageBO convertToBO(MessageVO messageVO) {
        MessageBO messageBO = new MessageBO();
        BeanUtils.copyProperties(messageVO, messageBO);
        messageBO.setPublishData(DateUtil.convertFromDateString(messageVO.getPublishData()));
        return messageBO;
    }

    private List<MessageVO> convertFromBOList(String messageBOJsonList) {
        List<MessageBO> messageBOList = JsonUtil.json2List(messageBOJsonList, MessageBO.class);
        List<MessageVO> messageVOList = new ArrayList<MessageVO>();
        for (MessageBO messageBO:messageBOList) {
            messageVOList.add(convertFromBO(messageBO));
        }
        return messageVOList;
    }
}
