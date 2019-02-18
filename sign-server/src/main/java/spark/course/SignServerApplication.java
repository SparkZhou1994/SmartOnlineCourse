package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
public class SignServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignServerApplication.class, args);
    }
}
