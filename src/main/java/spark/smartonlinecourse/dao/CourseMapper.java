package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Course;

import java.util.ArrayList;

/**
 * @ClassName CourseMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 20:03
 * @Version 1.0
 **/
public interface CourseMapper {
    ArrayList<Course> selectTop(Integer start);
}
