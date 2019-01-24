package spark.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @ClassName WebInvokerApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/21/2019 11:03 AM
 * @Version 1.0
 **/
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class WebInvokerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebInvokerApplication.class, args);
    }
}
