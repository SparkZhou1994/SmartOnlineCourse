package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignDiscussContentApi;
import spark.course.entity.bo.DiscussBO;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName DiscussContentFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:26 PM
 * @Version 1.0
 **/
@Component
public class DiscussContentFallback implements FeignDiscussContentApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscussContentFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;

    @Override
    public String selectByDiscussId(Integer discussId, Integer start, Integer size) throws BusinessException {
        DiscussContentBO discussContentBO = new DiscussContentBO();
        discussContentBO.setDiscussId(discussId);
        discussContentBO.setStart(start);
        discussContentBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussContentBO.class, discussContentBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByPrimaryKey(Integer discussContentId) throws BusinessException {
        DiscussContentBO discussContentBO = new DiscussContentBO();
        discussContentBO.setDiscussContentId(discussContentId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussContentBO.class, discussContentBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(DiscussContentBO discussContentBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussContentBO.class, discussContentBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void delete(Integer discussContentId) throws BusinessException {
        DiscussContentBO discussContentBO = new DiscussContentBO();
        discussContentBO.setDiscussContentId(discussContentId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussContentBO.class, discussContentBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String update(DiscussContentBO discussContentBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussContentBO.class, discussContentBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
