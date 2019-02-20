package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignHomeworkApi
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 1:43 AM
 * @Version 1.0
 **/
@FeignClient(name = "homework-server")
public interface FeignHomeworkApi extends IHomeworkApi{
}
