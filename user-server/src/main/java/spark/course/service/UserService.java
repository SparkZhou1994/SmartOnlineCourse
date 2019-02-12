package spark.course.service;

import spark.course.entity.bo.UserBO;
import spark.course.error.BusinessException;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Spark
 * @Date 1/22/2019 5:15 PM
 * @Version 1.0
 **/
public interface UserService {
    UserBO selectByUserId(Integer userId);
    UserBO insert(UserBO userBO);
    void deleteByUserId(Integer userId);
    UserBO updateUserByUserId(UserBO userBO) throws BusinessException;
    UserBO updatePasswordByUserId(UserBO userBO) throws BusinessException;
    UserBO selectByEmail(String email);
}
