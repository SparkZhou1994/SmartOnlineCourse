package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Discuss;

/**
 * @InterfaceName DiscussMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/4 20:10
 * @Version 1.0
 **/
public interface DiscussMapper {
    Discuss selectByCourseId(Integer courseId);
    Integer insertDiscuss(Discuss discuss);
    Integer updateDiscuss(Discuss discuss);
}
