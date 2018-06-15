package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.CourseMapper;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.service.CourseService;

import java.util.ArrayList;

/**
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 22:22
 * @Version 1.0
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ArrayList<Course> selectTop(Integer start) {
        ArrayList<Course> courseList=courseMapper.selectTop(start);
        return courseList;
    }
}
