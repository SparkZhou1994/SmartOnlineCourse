package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import spark.course.service.SendService;

/**
 * @ClassName MessageServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:26 AM
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableEurekaClient
@MapperScan("spark.course.dao")
@EnableBinding(value = {SendService.class, Source.class})
public class MessageServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServerApplication.class, args);
    }
}
