package spark.course.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.UserBO;
import spark.course.error.BusinessException;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Spark
 * @Date 1/24/2019 1:56 PM
 * @Version 1.0
 **/
@RestController("/user")
public class UserController extends BaseController {
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{userId:\\d+}", consumes = CommonConstants.BaseController.CONTENT_TYPE_FORMED)
    public String selectByUserId(@PathVariable("userId") Integer userId) {
        return userService.selectByUserId(userId);
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_FORMED)
    public String insertUser(@RequestBody UserBO user) throws BusinessException {
        return userService.insertUser(user);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_FORMED)
    public String updataUser(@RequestBody UserBO user) throws BusinessException {
        return userService.updataUser(user);
    }

    @PutMapping(value = "/password", consumes = CommonConstants.BaseController.CONTENT_TYPE_FORMED)
    public String updataUserPassword(UserBO user) throws BusinessException {
        return userService.updataUserPassword(user);
    }

    @DeleteMapping(value = "/{userId:\\d+}", consumes = CommonConstants.BaseController.CONTENT_TYPE_FORMED)
    public void deleteUser(@PathVariable("userId") Integer userId) throws BusinessException {
        userService.deleteUser(userId);
    }
}
