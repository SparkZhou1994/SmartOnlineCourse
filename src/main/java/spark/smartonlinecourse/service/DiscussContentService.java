package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.DiscussContent;
import spark.smartonlinecourse.entity.Key;

import java.util.List;

/**
 * @ClassName DiscussContentService
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/6 10:39
 * @Version 1.0
 **/
public interface DiscussContentService {
    List<DiscussContent> selectByDiscussIdAndPage(Integer discussId,Integer page);
    Boolean insertDiscussContent(DiscussContent discussContent);
    Integer selectAllCountByDiscussId(Integer discussId);
    Integer selectChooseCountByDiscussIdAndChoose(Integer discussId,Character choose);
}
