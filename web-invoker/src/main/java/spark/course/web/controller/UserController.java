package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.UserVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
import spark.course.error.EmBusinessError;
import spark.course.util.EncryptUtil;
import spark.course.util.FileUtil;
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
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{userId:\\d+}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByUserId(@PathVariable("userId") Integer userId) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(userService.selectByUserId(userId), UserBO.class)));
    }

    @PostMapping(value = "/password", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByEmail(@RequestBody UserBO userBO) throws BusinessException {
        UserBO userBOTemp = JsonUtil.json2Bean(userService.selectByEmail(userBO.getEmail()), UserBO.class);
        UserVO userVO = convertFromBO(userBOTemp);
        if (EncryptUtil.match(userBO.getEncryptPassword(), userBOTemp.getEncryptPassword())) {
            userVO.setLoginSuccessful("Success");
        } else {
            userVO.setLoginSuccessful("Fail");
        }
        return JsonUtil.convertToJson(userVO);
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String insertUser(@RequestBody UserBO user) throws BusinessException {
        user.setEncryptPassword(EncryptUtil.encrypt(user.getEncryptPassword()));
        String result = userService.insertUser(user);
        if (result.contains("true")) {
            throw new BusinessException(new ClassBusinessError(result));
        }
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(result, UserBO.class)));
    }

    @PostMapping(value = "/updata")
    public String updataUserWithAvatar(String version, String username, String telphone, String email, MultipartFile file) throws BusinessException {
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.User.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        UserVO userVO = new UserVO();
        userVO.setVersion(Long.parseLong(version));
        userVO.setUsername(username);
        userVO.setTelphone(telphone);
        userVO.setEmail(email);
        userVO.setAvatar(fileName);
        String result = userService.updataUser(convertToBO(userVO));
        if (result.contains("true")) {
            throw new BusinessException(new ClassBusinessError(result));
        }
        userVO.setVersion(Long.parseLong(version) + 1);
        return JsonUtil.convertToJson(userVO);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String updataUser(@RequestBody UserVO user) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(
                JsonUtil.json2Bean(userService.updataUser(convertToBO(user)), UserBO.class)));
    }

    @PutMapping(value = "/password", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String updataUserPassword(@RequestBody UserBO user) throws BusinessException {
        user.setEncryptPassword(EncryptUtil.encrypt(user.getEncryptPassword()));
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