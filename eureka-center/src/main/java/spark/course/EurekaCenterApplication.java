package spark.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaCenterApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 10:46 AM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCenterApplication.class, args);
    }
}
