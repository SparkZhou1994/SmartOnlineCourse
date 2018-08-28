package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.CourseWare;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

/**
 * @InterfaceName CourseWareMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/25 18:14
 * @Version 1.0
 **/
public interface CourseWareMapper {
    List<CourseWare> selectByCourseIdAndStartAndRow(Key key);
    Integer selectCountByCourseId(Integer courseId);
    Integer insertCourseWare(CourseWare courseWare);
}
