package spark.smartonlinecourse.service;

import java.util.Map;


public interface ChooseCourseService {
    Map<String,Object> chooseCourseLoad(Integer userId,Integer start);
}
