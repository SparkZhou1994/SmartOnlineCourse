package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignCourseWareApi;
import spark.course.entity.bo.CourseWareBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName CourseWareFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:26 PM
 * @Version 1.0
 **/
@Component
public class CourseWareFallback implements FeignCourseWareApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseWareFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public String selectByCourseWareId(Integer courseWareId) throws BusinessException {
        CourseWareBO courseWareBO = new CourseWareBO();
        courseWareBO.setCourseWareId(courseWareId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseWareBO.class, courseWareBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByCourseId(Integer courseId, Integer start, Integer size) throws BusinessException {
        CourseWareBO courseWareBO = new CourseWareBO();
        courseWareBO.setCourseId(courseId);
        courseWareBO.setStart(start);
        courseWareBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseWareBO.class, courseWareBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(CourseWareBO courseWareBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseWareBO.class, courseWareBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void delete(Integer courseWareId) throws BusinessException {
        CourseWareBO courseWareBO = new CourseWareBO();
        courseWareBO.setCourseWareId(courseWareId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseWareBO.class, courseWareBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String update(CourseWareBO courseWareBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseWareBO.class, courseWareBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}

