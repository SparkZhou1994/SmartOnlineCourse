package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

public interface ChooseCourseMapper {
    List<ChooseCourse> selectByUserId(Key key);
    Integer selectCountByUserId(Integer userId);
    Integer insertChooseCourse(Key key);
    List<Integer> selectChooseCourseId(Key key);
    Integer selectAvgScore(Integer courseId);
    Integer updateScore(ChooseCourse chooseCourse);
}