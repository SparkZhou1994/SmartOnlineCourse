package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import spark.course.service.hystrix.DiscussFallback;

/**
 * @ClassName FeignDiscussApi
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 4:04 PM
 * @Version 1.0
 **/
@FeignClient(name = "discuss-server", fallback = DiscussFallback.class)
public interface FeignDiscussApi extends IDiscussApi{
}
