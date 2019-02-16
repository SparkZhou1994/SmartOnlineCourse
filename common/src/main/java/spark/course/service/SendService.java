package spark.course.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @ClassName SendService
 * @Description TODO
 * @Author Spark
 * @Date 2/15/2019 7:35 PM
 * @Version 1.0
 **/
public interface SendService {
    @Output("insert")
    SubscribableChannel sendInsertMessage();

    @Output("update")
    SubscribableChannel sendUpdateMessage();

    @Output("delete")
    SubscribableChannel sendDeleteMessage();

    @Output("error")
    SubscribableChannel sendErrorMessage();

}
