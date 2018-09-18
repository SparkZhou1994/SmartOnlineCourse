package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.Message;

import java.util.List;

/**
 * @ClassName MessageMapper
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/17 17:19
 * @Version 1.0
 **/
public interface MessageMapper {
    Integer insertMessage(List messageList);
    List<Message> selectByChooseCouseId(Integer chooseCourseId);
}
