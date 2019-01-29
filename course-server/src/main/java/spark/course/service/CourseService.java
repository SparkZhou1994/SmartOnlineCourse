package spark.course.service;

import spark.course.entity.bo.CourseBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName CourseService
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 4:42 PM
 * @Version 1.0
 **/
public interface CourseService {
    CourseBO selectByCourseId(Integer courseId);
    List<CourseBO> selectCourseByCourseName(CourseBO courseBO);
    List<CourseBO> selectCourseSortByScore(CourseBO courseBO);
    CourseBO insert(CourseBO courseBO);
    void deleteByCourseId(Integer courseId);
    CourseBO updateByCourseId(CourseBO courseBO) throws BusinessException;
}
