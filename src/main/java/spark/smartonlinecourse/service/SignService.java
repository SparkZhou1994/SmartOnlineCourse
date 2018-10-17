package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Sign;

import java.util.List;

/**
 * @ClassName SignService
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/21 13:49
 * @Version 1.0
 **/
public interface SignService {
    Boolean releaseSign(Integer courseId, String code,Integer effectiveSecond,Integer userId);
    Boolean signIn(Integer courseId,Integer userId,String code);
    List<Sign> selectSignByCourseIdAndUserIdAndStartAndRow(Integer courseId,Integer userId,Integer page,Integer row);
    Integer selectCountByCourseIdAndUserId(Integer courseId,Integer userId);
    String signListToJson(Integer courseId,Integer userId,Integer page,Integer row,Boolean ownFlag);
}
