package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Sign;

import java.util.List;

public interface ChooseCourseMapper {
    List<ChooseCourse> selectByUserId(Key key);
    Integer selectCountByUserId(Integer userId);
    Integer insertChooseCourse(Key key);
    List<Integer> selectChooseCourseId(Key key);
}