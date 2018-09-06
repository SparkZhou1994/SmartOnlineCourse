package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Discuss;

import java.util.List;

/**
 * @ClassName DiscussService
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/6 10:04
 * @Version 1.0
 **/
public interface DiscussService {
    List<Discuss> selectByCourseIdAndPage(Integer courseId,Integer page);
    Boolean insertDiscuss(Discuss discuss);
    Boolean updateDiscuss(Integer discussId);
    Integer selectCountByCourseId(Integer courseId);
}
