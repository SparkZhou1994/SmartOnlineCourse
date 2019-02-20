package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignUserApi
 * @Description TODO
 * @Author Spark
 * @Date 1/24/2019 10:48 AM
 * @Version 1.0
 **/
@FeignClient(name = "user-server")
public interface FeignUserApi extends IUserApi{
}
