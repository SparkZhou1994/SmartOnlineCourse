package spark.course.service;

import spark.course.entity.bo.DiscussBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName DiscussService
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:47 PM
 * @Version 1.0
 **/
public interface DiscussService {
    List<DiscussBO> selectByChooseCourseId(Integer chooseCourseId);
    DiscussBO selectByDiscussId(Integer discussId);
    DiscussBO insert(DiscussBO discussBO);
    void delete(Integer discussId);
    DiscussBO update(DiscussBO discussBO) throws BusinessException;
}
