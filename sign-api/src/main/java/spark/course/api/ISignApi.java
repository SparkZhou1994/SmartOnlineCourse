package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.SignBO;
import spark.course.error.BusinessException;

/**
 * @ClassName ISignApi
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 11:17 AM
 * @Version 1.0
 **/
@RequestMapping("/signServer")
public interface ISignApi {
    @GetMapping(value = "/{signId}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectBySignId(@PathVariable(value = "signId") Integer signId);

    @GetMapping(value = "/{chooseCourseId}/{start}/{size}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCoureId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                 @PathVariable("start") Integer start,
                                 @PathVariable("size") Integer size);

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody SignBO signBO) throws BusinessException;

    @DeleteMapping(value = "/{signId}")
    void delete(@PathVariable(value = "signId") Integer signId);

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody SignBO signBO) throws BusinessException;
}
