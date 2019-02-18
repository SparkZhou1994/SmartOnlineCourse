package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;

/**
 * @ClassName ICourseApi
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 4:26 PM
 * @Version 1.0
 **/
@RequestMapping("/courseServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface ICourseApi {
    @GetMapping(value = "/{courseId}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseId(@PathVariable(value = "courseId") Integer courseId) throws BusinessException;

    @GetMapping(value = "/{courseName}/{start}/{size}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseName(@PathVariable(value = "courseName") String courseName,
                              @PathVariable(value = "start") Integer start,
                              @PathVariable(value = "size") Integer size) throws BusinessException ;

    @GetMapping(value = "/{start}/{size}")
    String selectSortByScore(@PathVariable(value = "start") Integer start,
                             @PathVariable(value = "size") Integer size) throws BusinessException;

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseBO courseBO) throws BusinessException;

    @DeleteMapping(value = "/{courseId}")
    void deleteCourse(@PathVariable(value = "courseId") Integer courseId) throws BusinessException;

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String updataCourseByCourseId(@RequestBody CourseBO courseBO) throws BusinessException;
}
