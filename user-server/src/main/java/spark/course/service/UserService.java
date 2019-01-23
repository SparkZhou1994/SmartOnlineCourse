package spark.course.service;

import spark.course.entity.bo.UserBO;

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
    UserBO deleteByUserId(Integer userId);
    UserBO updateByUserId(Integer userId);
}
