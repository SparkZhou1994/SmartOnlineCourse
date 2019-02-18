package spark.course.util;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import spark.course.entity.vo.LogMessage;

/**
 * @ClassName SendLogMessageUtil
 * @Description TODO
 * @Author Spark
 * @Date 2/15/2019 10:21 PM
 * @Version 1.0
 **/
@Component("sendLogMessageUtil")
public class SendLogMessageUtil {
    public static String sendInsertMessage(Object dataType, Object data) {
        LogMessage logMessage = new LogMessage("insert", dataType, data);
        return JsonUtil.convertToJson(logMessage);
    }

    public static String sendUpdateMessage(Object dataType, Object data) {
        LogMessage logMessage = new LogMessage("update", dataType, data);
        return JsonUtil.convertToJson(logMessage);
    }

    public static String sendDeleteMessage(Object dataType, Object data) {
        LogMessage logMessage = new LogMessage("delete", dataType, data);
        return JsonUtil.convertToJson(logMessage);
    }

    public static String sendErrorMessage(Object dataType, Object data) {
        LogMessage logMessage = new LogMessage("error", dataType, data);
        return JsonUtil.convertToJson(logMessage);
    }
}
