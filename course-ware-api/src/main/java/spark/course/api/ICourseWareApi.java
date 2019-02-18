package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.CourseWareBO;
import spark.course.error.BusinessException;

/**
 * @ClassName ICourseWareApi
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 9:56 AM
 * @Version 1.0
 **/
@RequestMapping("/courseWareServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface ICourseWareApi {
    @GetMapping(value = "/{courseWareId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseWareId(@PathVariable("courseWareId") Integer courseWareId) throws BusinessException;

    @GetMapping(value = "/{courseId}/{start}/{size}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseId(@PathVariable("courseId") Integer courseId,
                                  @PathVariable("start") Integer start,
                                  @PathVariable("size") Integer size) throws BusinessException;

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseWareBO courseWareBO) throws BusinessException;

    @DeleteMapping(value = "/{courseWareId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("courseWareId") Integer courseWareId) throws BusinessException;

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody CourseWareBO courseWareBO) throws BusinessException;
}
