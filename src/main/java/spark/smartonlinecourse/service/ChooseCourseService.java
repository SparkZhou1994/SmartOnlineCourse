package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Sign;

import java.util.Map;


public interface ChooseCourseService {
    Map<String,Object> chooseCourseLoad(Integer userId,Integer start);
    Boolean joinCourse(Integer courseId,Integer userId);
    Boolean ownCourse(Integer courseId,Integer userId);
}
