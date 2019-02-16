package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import spark.course.service.SendService;

/**
 * @ClassName SignServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 9:43 AM
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableEurekaClient
@MapperScan("spark.course.dao")
@EnableBinding(value = {SendService.class, Source.class})
public class SignServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignServerApplication.class, args);
    }
}
