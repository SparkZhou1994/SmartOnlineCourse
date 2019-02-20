package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.UserVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
import spark.course.util.JsonUtil;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Spark
 * @Date 1/24/2019 1:56 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{userId:\\d+}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByUserId(@PathVariable("userId") Integer userId) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(userService.selectByUserId(userId), UserBO.class)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String insertUser(@RequestBody UserBO user) throws BusinessException {
        String result = userService.insertUser(user);
        if (result.contains("true")) {
            throw new BusinessException(new ClassBusinessError(result));
        }
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(result, UserBO.class)));
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String updataUser(@RequestBody UserVO user) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(userService.updataUser(convertToBO(user)), UserBO.class)));
    }

    @PutMapping(value = "/password", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String updataUserPassword(@RequestBody UserBO user) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(userService.updataUserPassword(user), UserBO.class)));
    }

    @DeleteMapping(value = "/{userId:\\d+}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public void deleteUser(@PathVariable("userId") Integer userId) throws BusinessException {
        userService.deleteUser(userId);
    }

    private UserVO convertFromBO(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userBO, userVO);
        return userVO;
    }

    private UserBO convertToBO(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userVO, userBO);
        return userBO;
    }
}