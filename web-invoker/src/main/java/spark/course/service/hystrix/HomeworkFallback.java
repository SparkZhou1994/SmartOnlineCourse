package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignHomeworkApi;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.entity.bo.HomeworkBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName HomeworkFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:26 PM
 * @Version 1.0
 **/
@Component
public class HomeworkFallback implements FeignHomeworkApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public String selectByHomeworkId(Integer homeworkId) throws BusinessException {
        HomeworkBO homeworkBO = new HomeworkBO();
        homeworkBO.setHomeworkId(homeworkId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(HomeworkBO.class, homeworkBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size) throws BusinessException {
        HomeworkBO homeworkBO = new HomeworkBO();
        homeworkBO.setChooseCourseId(chooseCourseId);
        homeworkBO.setStart(start);
        homeworkBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(HomeworkBO.class, homeworkBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(HomeworkBO homeworkBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(HomeworkBO.class, homeworkBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void delete(Integer homeworkId) throws BusinessException {
        HomeworkBO homeworkBO = new HomeworkBO();
        homeworkBO.setHomeworkId(homeworkId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(HomeworkBO.class, homeworkBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String update(HomeworkBO homeworkBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(HomeworkBO.class, homeworkBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
