package spark.course.util;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @ClassName TestDateUtil
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 11:27 AM
 * @Version 1.0
 **/
public class TestDateUtil {
    @Test
    public void testConvertFromString() {
        String localDateTimeString = "2019-02-03 10:55:12";
        LocalDateTime localDateTime = DateUtil.convertFromString(localDateTimeString);
        System.out.print("+++++++++++++++++++++++++++++++++++++++++");
        System.out.print(localDateTime);
        System.out.print("+++++++++++++++++++++++++++++++++++++++++");
    }
}
