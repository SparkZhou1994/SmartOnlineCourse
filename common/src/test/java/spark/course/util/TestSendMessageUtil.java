package spark.course.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spark.course.entity.vo.LogMessage;

/**
 * @ClassName TestSendMessageUtil
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 3:15 PM
 * @Version 1.0
 **/
public class TestSendMessageUtil {
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Test
    public void testConvertFromString() {
        LogMessage logMessage = new LogMessage("A","B","C");
        String json = sendLogMessageUtil.sendInsertMessage(LogMessage.class, logMessage);
        System.out.print(json);
    }
}
