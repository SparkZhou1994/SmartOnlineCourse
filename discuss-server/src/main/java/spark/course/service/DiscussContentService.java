package spark.course.service;

import spark.course.entity.bo.DiscussContentBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName DiscussContentService
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 3:06 PM
 * @Version 1.0
 **/
public interface DiscussContentService {
    List<DiscussContentBO> selectByDiscussId(Integer discussId,Integer start,Integer size);
    DiscussContentBO selectByPrimaryKey(Integer discussContentId);
    DiscussContentBO insert(DiscussContentBO discussContentBO);
    void delete(Integer discussContentId);
    DiscussContentBO update(DiscussContentBO discussContentBO) throws BusinessException;
    DiscussContentBO selectVoteResultByDiscussId(Integer discussId);
}
