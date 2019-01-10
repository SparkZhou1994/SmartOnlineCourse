package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

/**
 * @ClassName CourseMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 20:03
 * @Version 1.0
 **/
public interface CourseMapper {
    List<Course> selectTop(Integer start);
    Integer insertCourse(Course course);
    Course selectCourseByCourseId(Integer courseId);
    Integer selectCoursIdByCourseNameAndUserId(Key key);
    Integer updateScore(Course course);
    List<Course> selectCourseByCourseName(String courseName);
}
