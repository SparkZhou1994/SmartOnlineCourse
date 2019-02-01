package spark.course.api;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName IDiscussContentApi
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 3:43 PM
 * @Version 1.0
 **/
@RequestMapping("/discussContentServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface IDiscussContentApi {
    @GetMapping(value = "/{discussId}/{start}/{size}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByDiscussId(@PathVariable("discussId") Integer discussId,
                             @PathVariable("start") Integer start,
                             @PathVariable("size") Integer size);

    @GetMapping(value = "/{discussContentId}")
    String selectByPrimaryKey(@PathVariable(value = "discussContentId") Integer discussContentId);

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody DiscussContentBO discussContentBO);

    @DeleteMapping(value = "/{discussContentId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable(value = "discussContentId") Integer discussContentId);

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody DiscussContentBO discussContentBO) throws BusinessException;
}
