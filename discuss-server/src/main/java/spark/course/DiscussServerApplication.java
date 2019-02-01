package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName DiscussServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:45 PM
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableEurekaClient
@MapperScan("spark.course.dao")
public class DiscussServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscussServerApplication.class, args);
    }
}
