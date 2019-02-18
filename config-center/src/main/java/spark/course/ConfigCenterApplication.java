package spark.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName EurekaConfigCenterApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/15/2019 1:56 PM
 * @Version 1.0
 **/

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }
}
