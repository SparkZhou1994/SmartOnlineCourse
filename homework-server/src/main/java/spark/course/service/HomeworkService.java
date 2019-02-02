package spark.course.service;

import spark.course.entity.bo.HomeworkBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName HomeworkService
 * @Description TODO
 * @Author Spark
 * @Date 2/2/2019 2:23 PM
 * @Version 1.0
 **/
public interface HomeworkService {
    HomeworkBO selectByHomeworkId(Integer homeworkId);
    List<HomeworkBO> selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size);
    HomeworkBO insert(HomeworkBO homeworkBO);
    void delete(Integer homeworkId);
    HomeworkBO update(HomeworkBO homeworkBO) throws BusinessException;
}
