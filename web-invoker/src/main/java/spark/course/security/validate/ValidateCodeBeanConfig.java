package spark.course.security.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spark.course.security.validate.image.ImageCodeGenerator;
import spark.course.security.SecurityProperties;

/**
 * @ClassName ValidateCodeBeanConfig
 * @Description TODO
 * @Author Spark
 * @Date 2/10/2019 7:41 PM
 * @Version 1.0
 **/
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
}