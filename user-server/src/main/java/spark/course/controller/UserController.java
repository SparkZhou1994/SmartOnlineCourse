package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.IUserApi;
import spark.course.entity.bo.UserBO;
import spark.course.error.BusinessException;
import spark.course.service.UserService;
import spark.course.util.JsonUtil;
import spark.course.validator.ValidationExceptionHandler;
import spark.course.validator.ValidationResult;

import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Spark
 * @Date 1/24/2019 10:53 AM
 * @Version 1.0
 **/
@RestController
public class UserController extends ValidationExceptionHandler implements IUserApi {
    @Autowired
    private UserService userService;

    @Override
    public String selectByUserId(@PathVariable("userId") Integer userId) throws BusinessException {
        return JsonUtil.convertToJson(userService.selectByUserId(userId));
    }

    @Override
    public String insertUser(@RequestBody @Valid UserBO user) throws BusinessException {
        return JsonUtil.convertToJson(userService.insert(user));
    }

    @Override
    public String updataUser(@RequestBody UserBO user) throws BusinessException {
        return JsonUtil.convertToJson(userService.updateUserByUserId(user));
    }

    @Override
    public String updataUserPassword(@RequestBody UserBO user) throws BusinessException {
        return JsonUtil.convertToJson(userService.updatePasswordByUserId(user));
    }

    @Override
    public void deleteUser(@PathVariable("userId") Integer userId) throws BusinessException {
        userService.deleteByUserId(userId);
    }

    @Override
    public String selectByEmail(@PathVariable("email") String email){
        return JsonUtil.convertToJson(userService.selectByEmail(email));
    }
}
