package spark.course.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Author Spark
 * @Date 1/24/2019 11:06 AM
 * @Version 1.0
 **/
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static JavaTimeModule timeModule = new JavaTimeModule();

    static {
        timeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(timeModule);
    }

    public static String convertToJson( Object object ) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return "";
        }
    }

    public static <T> T json2Bean(String json, Class<T> T) {
        try {
            T obj = objectMapper.readValue(json, T);
            return obj;
        } catch (Exception e) {

            return null;
        }
    }

    public static <T> List<T> json2List(String json, Class<T> T) {
        JavaType javaType = getCollectionType(ArrayList.class, T);
        List<T> lst;
        try {
            lst = objectMapper.readValue(json, javaType);
        } catch (Exception e) {

            return null;
        }
        return lst;
    }

    public static <T> Map<String,T> json2Map(String json, Class<T> T) {
        JavaType javaType = getCollectionType(HashMap.class, String.class, T);
        Map<String,T> map = new HashMap<String,T>();
        try {
            map = objectMapper.readValue(json,javaType);
        } catch (Exception e) {

            return null;
        }
        return map;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
