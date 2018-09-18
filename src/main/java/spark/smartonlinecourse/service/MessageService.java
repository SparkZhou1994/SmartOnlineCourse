package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.Message;

import java.util.List;

/**
 * @ClassName MessageService
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/17 17:35
 * @Version 1.0
 **/
public interface MessageService {
    Boolean homeworkMessage();
    Boolean signMessage();
    List<Message> selectByChooseCouseId(Integer chooseCourseId);
    List<Message> selectByUserId(Integer userId);
}
