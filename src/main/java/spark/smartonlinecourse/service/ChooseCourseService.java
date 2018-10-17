package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Key;

import java.util.List;
import java.util.Map;


public interface ChooseCourseService {
    Map<String,Object> chooseCourseLoad(Integer userId,Integer start);
    Boolean joinCourse(Integer courseId,Integer userId);
    List<Integer> selectChooseCourseId(Key key);
}
