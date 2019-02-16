package spark.course.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import spark.course.entity.vo.LogMessage;
import spark.course.service.SendService;

/**
 * @ClassName SendMessageUtil
 * @Description TODO
 * @Author Spark
 * @Date 2/15/2019 10:21 PM
 * @Version 1.0
 **/
@Component("sendMessageUtil")
public class SendMessageUtil {
    @Autowired
    SendService sendService;
    public void sendInsertMessage(Object dataType, String dataJson) {
        LogMessage logMessage = new LogMessage("insert", dataType, dataJson);
        Message msg = MessageBuilder.withPayload(logMessage.toString().getBytes()).build();
        sendService.sendInsertMessage().send(msg);
    }

    public void sendUpdateMessage(Object dataType, String dataJson) {
        LogMessage logMessage = new LogMessage("update", dataType, dataJson);
        Message msg = MessageBuilder.withPayload(logMessage.toString().getBytes()).build();
        sendService.sendUpdateMessage().send(msg);
    }

    public void sendDeleteMessage(Object dataType, String dataJson) {
        LogMessage logMessage = new LogMessage("delete", dataType, dataJson);
        Message msg = MessageBuilder.withPayload(logMessage.toString().getBytes()).build();
        sendService.sendDeleteMessage().send(msg);
    }

    public void sendErrorMessage(Object dataType, String dataJson) {
        LogMessage logMessage = new LogMessage("error", dataType, dataJson);
        Message msg = MessageBuilder.withPayload(logMessage.toString().getBytes()).build();
        sendService.sendErrorMessage().send(msg);
    }
}
