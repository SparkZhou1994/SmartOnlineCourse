package spark.course.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.UserBO;
import spark.course.error.BusinessException;

/**
 * @ClassName IUserApi
 * @Description TODO
 * @Author Spark
 * @Date 1/24/2019 9:30 AM
 * @Version 1.0
 **/
@RequestMapping("/userServer")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public interface IUserApi {
    /**
    * @author Spark
    * @Description
    * @Date 9:34 AM 1/24/2019
    * @Param [userId]
    * @return spark.course.entity.bo.UserBO(JSON)
    **/
    @GetMapping(value = "/{userId}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByUserId(@PathVariable(value = "userId") Integer userId);

    /**
    * @author Spark
    * @Description 
    * @Date 10:24 AM 1/24/2019
    * @Param [user]
    * @return spark.course.entity.bo.UserBO(JSON)
    **/
    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insertUser(@RequestBody UserBO user) throws BusinessException;

    /**
    * @author Spark
    * @Description
    * @Date 10:41 AM 1/24/2019
    * @Param [user]
    * @return spark.course.entity.bo.UserBO(JSON)
    **/
    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String updataUser(@RequestBody UserBO user) throws BusinessException;

    /**
    * @author Spark
    * @Description
    * @Date 1:53 PM 1/24/2019
    * @Param [user]
    * @return java.lang.String
    **/
    @PutMapping(value = "/password", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String updataUserPassword(@RequestBody UserBO user) throws BusinessException;

    /**
    * @author Spark
    * @Description 
    * @Date 10:45 AM 1/24/2019
    * @Param [userId]
    * @return void
    **/
    @DeleteMapping(value = "/{userId}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void deleteUser(@PathVariable(value = "userId") Integer userId) throws BusinessException;
}
