package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Course;

import java.util.ArrayList;

/**
 * @ClassName CourseService
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 22:05
 * @Version 1.0
 **/
public interface CourseService {
    ArrayList<Course> selectTop(Integer start);
}
