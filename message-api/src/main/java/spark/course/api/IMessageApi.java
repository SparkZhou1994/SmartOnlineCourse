package spark.course.api;

import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.MessageBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName IMessageApi
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:45 AM
 * @Version 1.0
 **/
@RequestMapping("/messageServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface IMessageApi {
    @GetMapping(value = "/{messageId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByMessageId(@PathVariable("messageId") Integer messageId) throws BusinessException;

    @GetMapping(value = "/{chooseCourseId}/{start}/{size}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                            @PathVariable("start") Integer start,
                            @PathVariable("size") Integer size) throws BusinessException;

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody MessageBO messageBO) throws BusinessException;

    @DeleteMapping(value = "/{messageId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("messageId") Integer messageId) throws BusinessException;

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody MessageBO messageBO) throws BusinessException;
}
