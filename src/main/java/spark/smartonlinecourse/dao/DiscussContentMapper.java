package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.DiscussContent;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

/**
 * @InterfaceName DiscussContentMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/4 20:12
 * @Version 1.0
 **/
public interface DiscussContentMapper {
    List<DiscussContent> selectByDiscussIdAndStart(Key key);
    Integer insertDiscussContent(DiscussContent discussContent);
    Integer selectAllCountByDiscussId(Integer discussId);
    Integer selectChooseCountByDiscussIdAndChoose(DiscussContent discussContent);
}
