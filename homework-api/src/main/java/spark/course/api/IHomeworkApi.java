package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.HomeworkBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName IHomeworkApi
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 1:31 AM
 * @Version 1.0
 **/
@RequestMapping("/homeworkServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface IHomeworkApi {
    @GetMapping(value = "/{homeworkId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByHomeworkId(@PathVariable("homeworkId") Integer homeworkId);

    @GetMapping(value = "/{chooseCourseId}/{start}/{size}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                  @PathVariable("start") Integer start,
                                  @PathVariable("size") Integer size);

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody HomeworkBO homeworkBO);

    @DeleteMapping(value = "/{homeworkId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("homeworkId") Integer homeworkId);

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody HomeworkBO homeworkBO) throws BusinessException;
}
