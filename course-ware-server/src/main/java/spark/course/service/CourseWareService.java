package spark.course.service;

import spark.course.entity.bo.CourseWareBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName CourseWareService
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 8:20 PM
 * @Version 1.0
 **/
public interface CourseWareService {
    CourseWareBO selectByCourseWareId(Integer courseWareId);
    List<CourseWareBO>  selectByCourseId(Integer courseId, Integer start, Integer size);
    CourseWareBO insert(CourseWareBO courseWareBO);
    void delete(Integer courseWareId);
    CourseWareBO update(CourseWareBO courseWareBO) throws BusinessException;
}
