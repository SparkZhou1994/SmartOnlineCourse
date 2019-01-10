package spark.smartonlinecourse.service;

import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Course;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CourseService
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 22:05
 * @Version 1.0
 **/
public interface CourseService {
    List<Course> selectTop(Integer start);
    Course insertCourse(Course course, MultipartFile file, HttpServletRequest request,Integer userId);
    Course selectCourseByCourseId(Integer courseId);
    Boolean ownCourse(Integer courseId,Integer userId);
    Boolean updateScore(ChooseCourse chooseCourse);
    List<Course> selectCourseByCourseName(String courseName);
}