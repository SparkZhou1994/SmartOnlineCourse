package spark.course.service;

import spark.course.entity.bo.SignBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName SignService
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 10:31 AM
 * @Version 1.0
 **/
public interface SignService {
    SignBO selectBySignId(Integer signId);
    List<SignBO> selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size);
    SignBO insert(SignBO signBO);
    void delete(Integer signId);
    SignBO update(SignBO signBO) throws BusinessException;
 }
