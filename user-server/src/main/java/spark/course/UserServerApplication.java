package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName UserServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/21/2019 1:07 PM
 * @Version 1.0
 **/

@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableDiscoveryClient
@MapperScan("spark.course.dao")
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
