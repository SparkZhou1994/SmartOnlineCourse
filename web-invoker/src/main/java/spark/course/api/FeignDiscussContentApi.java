package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
/**
 * @ClassName FeignDiscussContentApi
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 4:05 PM
 * @Version 1.0
 **/
@FeignClient(name = "discuss-server")
public interface FeignDiscussContentApi extends IDiscussContentApi{
}
