package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignDiscussApi;
import spark.course.entity.bo.CourseWareBO;
import spark.course.entity.bo.DiscussBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName DiscussFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:26 PM
 * @Version 1.0
 **/
@Component
public class DiscussFallback implements FeignDiscussApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscussFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;

    @Override
    public String selectByChooseCourseId(Integer chooseCourseId) throws BusinessException {
        DiscussBO discussBO = new DiscussBO();
        discussBO.setChooseCourseId(chooseCourseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussBO.class, discussBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByDiscussId(Integer discussId) throws BusinessException {
        DiscussBO discussBO = new DiscussBO();
        discussBO.setDiscussId(discussId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussBO.class, discussBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(DiscussBO discussBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussBO.class, discussBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void delete(Integer discussId) throws BusinessException {
        DiscussBO discussBO = new DiscussBO();
        discussBO.setDiscussId(discussId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussBO.class, discussBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String update(DiscussBO discussBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(DiscussBO.class, discussBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
