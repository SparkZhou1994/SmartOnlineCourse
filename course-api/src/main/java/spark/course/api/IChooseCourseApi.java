package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.vo.CourseVO;
import spark.course.error.BusinessException;

/**
 * @ClassName IChooseCourseApi
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 4:35 PM
 * @Version 1.0
 **/
@RequestMapping("/chooseCourseServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface IChooseCourseApi {
    @GetMapping(value = "/{chooseCourseId}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable(value = "chooseCourseId") Integer chooseCourseId);

    @GetMapping(value = "/{userId}/{courseId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectChooseCourseIdByUserIdAndCourseId(@PathVariable(value = "userId") Integer userId,
                                                   @PathVariable(value = "courseId") Integer courseId);

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseBO courseBO);

    @DeleteMapping(value = "/{chooseCourseId}")
    void deleteChooseCourse(@PathVariable(value = "chooseCourseId") Integer chooseCourseId);

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String updateByChooseCourseId(@RequestBody CourseBO courseBO) throws BusinessException;
}
