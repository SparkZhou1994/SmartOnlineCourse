package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignUserApi;
import spark.course.entity.bo.UserBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName UserFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:27 PM
 * @Version 1.0
 **/
@Component
public class UserFallback implements FeignUserApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public String selectByUserId(Integer userId) throws BusinessException {
        UserBO userBO = new UserBO();
        userBO.setUserId(userId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(UserBO.class, userBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insertUser(UserBO user) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(UserBO.class, user));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String updataUser(UserBO user) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(UserBO.class, user));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String updataUserPassword(UserBO user) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(UserBO.class, user));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void deleteUser(Integer userId) throws BusinessException {
        UserBO userBO = new UserBO();
        userBO.setUserId(userId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(UserBO.class, userBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByEmail(String email) throws BusinessException {
        UserBO userBO = new UserBO();
        userBO.setEmail(email);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(UserBO.class, userBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
