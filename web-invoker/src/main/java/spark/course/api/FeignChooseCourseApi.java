package spark.course.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @ClassName FeignChooseCourseApi
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 5:21 PM
 * @Version 1.0
 **/
@FeignClient("course-server")
public interface FeignChooseCourseApi extends IChooseCourseApi{
}
