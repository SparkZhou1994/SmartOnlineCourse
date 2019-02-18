package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.DiscussBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName IDiscussApi
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:46 PM
 * @Version 1.0
 **/
@RequestMapping("/discussServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface IDiscussApi {
    @GetMapping(value = "/chooseCourseId/{chooseCourseId}",consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId) throws BusinessException;

    @GetMapping(value = "/{discussId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByDiscussId(@PathVariable("discussId") Integer discussId) throws BusinessException;

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody DiscussBO discussBO) throws BusinessException;

    @DeleteMapping(value = "/{discussId",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("discussId") Integer discussId) throws BusinessException;

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody DiscussBO discussBO) throws BusinessException;
}
