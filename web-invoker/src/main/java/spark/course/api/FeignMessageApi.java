package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import spark.course.service.hystrix.MessageFallback;

/**
 * @ClassName FeignMessageApi
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:53 AM
 * @Version 1.0
 **/
@FeignClient(name = "message-server", fallback = MessageFallback.class)
public interface FeignMessageApi extends IMessageApi {
}
