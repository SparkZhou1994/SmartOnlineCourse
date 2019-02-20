package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignCourseApi
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 12:23 PM
 * @Version 1.0
 **/
@FeignClient(name = "course-server")
public interface FeignCourseApi extends ICourseApi {
}
