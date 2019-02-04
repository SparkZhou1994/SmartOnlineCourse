package spark.course.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 1:33 PM
 * @Version 1.0
 **/
public class DateUtil {
    private static final String dateTimeFormatterPattern = "yyyy-MM-dd HH:mm:ss";
    private static final String dateFormatterPattern = "yyyy-MM-dd";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatterPattern);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormatterPattern);

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertFromString(String localDateTimeString) {
        return LocalDateTime.parse(localDateTimeString,dateTimeFormatter);
    }

    public static String convertFromLocalDateTime(LocalDateTime localDateTime) {
        return dateTimeFormatter.format(localDateTime);
    }

    public static String convertFromLocalDate(LocalDate localDate) {
        return dateFormatter.format(localDate);
    }

    public static LocalDate convertFromDateString(String localDate) {
        return LocalDate.parse(localDate,dateFormatter);
    }
}
