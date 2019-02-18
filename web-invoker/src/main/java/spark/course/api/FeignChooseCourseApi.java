package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import spark.course.service.hystrix.ChooseCourseFallback;

/**
 * @ClassName FeignChooseCourseApi
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 5:21 PM
 * @Version 1.0
 **/
@FeignClient(name = "course-server", fallback = ChooseCourseFallback.class)
public interface FeignChooseCourseApi extends IChooseCourseApi{
}
