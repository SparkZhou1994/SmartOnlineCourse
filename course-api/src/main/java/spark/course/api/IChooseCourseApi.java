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
    String selectByChooseCourseId(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) throws BusinessException;

    @GetMapping(value = "/{userId}/{courseId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectChooseCourseByUserIdAndCourseId(@PathVariable(value = "userId") Integer userId,
                                                   @PathVariable(value = "courseId") Integer courseId) throws BusinessException;

    @GetMapping(value = "/courseId/{courseId}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseId(@PathVariable(value = "courseId") Integer courseId) throws BusinessException;

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseBO courseBO) throws BusinessException;

    @DeleteMapping(value = "/{chooseCourseId}")
    void deleteChooseCourse(@PathVariable(value = "chooseCourseId") Integer chooseCourseId) throws BusinessException;

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String updateByChooseCourseId(@RequestBody CourseBO courseBO) throws BusinessException;
}
