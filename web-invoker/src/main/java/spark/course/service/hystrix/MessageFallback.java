package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignMessageApi;
import spark.course.entity.bo.HomeworkBO;
import spark.course.entity.bo.MessageBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName MessageFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:27 PM
 * @Version 1.0
 **/
@Component
public class MessageFallback implements FeignMessageApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public String selectByMessageId(Integer messageId) throws BusinessException {
        MessageBO messageBO = new MessageBO();
        messageBO.setMessageId(messageId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(MessageBO.class, messageBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size) throws BusinessException {
        MessageBO messageBO = new MessageBO();
        messageBO.setChooseCourseId(chooseCourseId);
        messageBO.setStart(start);
        messageBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(MessageBO.class, messageBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(MessageBO messageBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(MessageBO.class, messageBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void delete(Integer messageId) throws BusinessException {
        MessageBO messageBO = new MessageBO();
        messageBO.setMessageId(messageId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(MessageBO.class, messageBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String update(MessageBO messageBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(MessageBO.class, messageBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
