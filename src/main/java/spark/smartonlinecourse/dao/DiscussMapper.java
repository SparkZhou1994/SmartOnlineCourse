package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Discuss;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

/**
 * @InterfaceName DiscussMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/4 20:10
 * @Version 1.0
 **/
public interface DiscussMapper {
    List<Discuss> selectByCourseIdAndStart(Key key);
    Integer insertDiscuss(Discuss discuss);
    Integer updateDiscuss(Discuss discuss);
    Integer selectCountByCourseId(Integer courseId);
}
