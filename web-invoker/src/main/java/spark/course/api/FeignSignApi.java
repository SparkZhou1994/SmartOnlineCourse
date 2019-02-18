package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import spark.course.service.hystrix.SignFallback;

/**
 * @ClassName FeignSignApi
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 11:53 AM
 * @Version 1.0
 **/
@FeignClient(name = "sign-server", fallback = SignFallback.class)
public interface FeignSignApi extends ISignApi{
}
