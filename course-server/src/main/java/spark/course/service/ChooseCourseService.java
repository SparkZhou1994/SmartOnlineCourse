package spark.course.service;

import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;

/**
 * @ClassName ChooseCourseService
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 4:13 PM
 * @Version 1.0
 **/
public interface ChooseCourseService {
    CourseBO selectByChooseCourseId(Integer chooseCourseId);
    CourseBO selectChooseCourseIdByUserIdAndCourseId(CourseBO courseBO);
    CourseBO insert(CourseBO courseBO);
    void deleteByChooseCourseId(Integer chooseCourseId);
    CourseBO updateByChooseCourseId(CourseBO courseBO) throws BusinessException;
}
