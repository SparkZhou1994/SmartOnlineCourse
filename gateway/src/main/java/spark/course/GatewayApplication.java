package spark.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import spark.course.service.ReceiveService;

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
@EnableBinding(value = {ReceiveService.class, Sink.class})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @StreamListener("error")
    public void receiveError(byte[] msg) {
        System.out.println("接收到的消息：  " + new String(msg));
    }
    @StreamListener("insert")
    public void receiveInsert(byte[] msg) {
        System.out.println("接收到的消息：  " + new String(msg));
    }
    @StreamListener("update")
    public void receiveUpdate(byte[] msg) {
        System.out.println("接收到的消息：  " + new String(msg));
    }
    @StreamListener("delete")
    public void receiveDelete(byte[] msg) {
        System.out.println("接收到的消息：  " + new String(msg));
    }

}
