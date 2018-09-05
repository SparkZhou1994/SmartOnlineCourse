package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Homework;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

/**
 * @InterfaceName HomeworkMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/29 23:03
 * @Version 1.0
 **/
public interface HomeworkMapper {
    List<Homework> selectHomework(Key key);
    Integer insertHomework(List homeworkList);
    Integer updateHomework(Homework homework);
}
