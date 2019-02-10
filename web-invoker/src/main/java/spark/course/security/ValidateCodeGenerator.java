package spark.course.security;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeGenerator
 * @Description TODO
 * @Author Spark
 * @Date 2/10/2019 7:33 PM
 * @Version 1.0
 **/
public interface ValidateCodeGenerator {
    ImageCode generate(ServletWebRequest request);
}
