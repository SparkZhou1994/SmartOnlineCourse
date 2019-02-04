package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignMessageApi
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:53 AM
 * @Version 1.0
 **/
@FeignClient("message-server")
public interface FeignMessageApi extends IMessageApi {
}
