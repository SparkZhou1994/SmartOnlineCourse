package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignChooseCourseApi;
import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName ChooseCourseFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:25 PM
 * @Version 1.0
 **/
@Component
public class ChooseCourseFallback implements FeignChooseCourseApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;

    @Override
    public String selectByChooseCourseId(Integer chooseCourseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setChooseCourseId(chooseCourseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectChooseCourseByUserIdAndCourseId(Integer userId, Integer courseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setUserId(userId);
        courseBO.setCourseId(courseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByCourseId(Integer courseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setCourseId(courseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(CourseBO courseBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void deleteChooseCourse(Integer chooseCourseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setChooseCourseId(chooseCourseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String updateByChooseCourseId(CourseBO courseBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
