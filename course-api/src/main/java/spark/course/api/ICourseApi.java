package spark.course.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ICourseApi
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 4:26 PM
 * @Version 1.0
 **/
@RequestMapping("/courseServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface ICourseApi {
}
