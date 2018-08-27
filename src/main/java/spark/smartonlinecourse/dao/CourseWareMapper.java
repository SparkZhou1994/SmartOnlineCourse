package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.CourseWare;
import spark.smartonlinecourse.entity.Key;

/**
 * @InterfaceName CourseWareMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/25 18:14
 * @Version 1.0
 **/
public interface CourseWareMapper {
    CourseWare selectByCourseIdAndStartAndRow(Key key);
}
