package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

public interface ChooseCourseMapper {
    List<ChooseCourse> selectByUserId(Key key);

    Integer getCountByUserId(Integer userId);
}
