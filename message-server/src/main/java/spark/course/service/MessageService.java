package spark.course.service;

import spark.course.entity.bo.MessageBO;
import spark.course.error.BusinessException;

import java.util.List;

/**
 * @ClassName MessageService
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:29 AM
 * @Version 1.0
 **/
public interface MessageService {
    MessageBO selectByMessageId(Integer messageId);
    List<MessageBO> selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size);
    MessageBO insert(MessageBO messageBO);
    void delete(Integer messageId);
    MessageBO updata(MessageBO messageBO) throws BusinessException;
}
