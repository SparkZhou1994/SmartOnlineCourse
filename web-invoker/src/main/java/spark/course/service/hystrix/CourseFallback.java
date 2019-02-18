package spark.course.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.course.api.FeignCourseApi;
import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.SendLogMessageUtil;

/**
 * @ClassName CourseFallback
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 10:25 PM
 * @Version 1.0
 **/
@Component
public class CourseFallback implements FeignCourseApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseFallback.class);
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public String selectByCourseId(Integer courseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setCourseId(courseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectByCourseName(String courseName, Integer start, Integer size) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setCourseName(courseName);
        courseBO.setStart(start);
        courseBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String selectSortByScore(Integer start, Integer size) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setStart(start);
        courseBO.setSize(size);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String insert(CourseBO courseBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public void deleteCourse(Integer courseId) throws BusinessException {
        CourseBO courseBO = new CourseBO();
        courseBO.setCourseId(courseId);
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }

    @Override
    public String updataCourseByCourseId(CourseBO courseBO) throws BusinessException {
        LOGGER.error(sendLogMessageUtil.sendFallbackErrorMessage(CourseBO.class, courseBO));
        throw new BusinessException(EmBusinessError.SERVER_PARALYSIS);
    }
}
