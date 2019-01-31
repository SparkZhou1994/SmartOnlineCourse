package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignDiscussApi
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 4:04 PM
 * @Version 1.0
 **/
@FeignClient("discuss-server")
public interface FeignDiscussApi extends IDiscussApi{
}
