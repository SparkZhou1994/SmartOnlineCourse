package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Sign;

import java.util.List;

/**
 * @ClassName SignMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/21 13:45x
 * @Version 1.0
 **/
public interface SignMapper {
    Integer selectBatchByCourseId(Integer courseId);
    Integer insertSign(Sign sign);
    Integer updateSign(Sign sign);
    Sign selectSignByChooseCourseIdAndBatch(Key key);
    List<Sign> selectSignByCourseIdAndUserIdAndStartAndRow(Key key);
    Integer selectCountByCourseIdAndUserId(Key key);
}
