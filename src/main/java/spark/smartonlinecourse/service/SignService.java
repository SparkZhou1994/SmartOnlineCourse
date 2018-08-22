package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Sign;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName SignService
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/21 13:49
 * @Version 1.0
 **/
public interface SignService {
    Boolean releaseSign(Integer courseId, String code,Integer effectiveSecond);
    Boolean signIn(Integer courseId,Integer userId,String code);
    List<Sign> selectSignByCourseIdAndUserIdAndStart(Integer courseId,Integer userId,Integer page);
    Integer selectCountByCourseIdAndUserId(Integer courseId,Integer userId);
}
