package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignSignApi;
import spark.course.entity.bo.MessageBO;
import spark.course.entity.bo.SignBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName SignFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:27 PM
 * @Version 1.0
 **/
@Component
public class SignFallback implements FeignSignApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public String selectBySignId(Integer signId) throws BusinessException {
        SignBO signBO = new SignBO();
        signBO.setSignId(signId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(SignBO.class, signBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByChooseCoureId(Integer chooseCourseId, Integer start, Integer size) throws BusinessException {
        SignBO signBO = new SignBO();
        signBO.setChooseCourseId(chooseCourseId);
        signBO.setStart(start);
        signBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(SignBO.class, signBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(SignBO signBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(SignBO.class, signBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void delete(Integer signId) throws BusinessException {
        SignBO signBO = new SignBO();
        signBO.setSignId(signId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(SignBO.class, signBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String update(SignBO signBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(SignBO.class, signBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
