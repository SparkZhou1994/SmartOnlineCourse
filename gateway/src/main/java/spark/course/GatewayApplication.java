package spark.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName GatewayApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/15/2019 11:21 PM
 * @Version 1.0
 **/
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
