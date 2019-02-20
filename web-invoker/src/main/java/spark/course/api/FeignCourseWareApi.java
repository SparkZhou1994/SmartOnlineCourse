package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignCourseWareApi
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 10:16 AM
 * @Version 1.0
 **/
@FeignClient(name = "course-ware-server")
public interface FeignCourseWareApi extends ICourseWareApi {
}
