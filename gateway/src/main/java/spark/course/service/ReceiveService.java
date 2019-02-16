package spark.course.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @ClassName ReceiveService
 * @Description TODO
 * @Author Spark
 * @Date 2/16/2019 2:33 PM
 * @Version 1.0
 **/
public interface ReceiveService {
    @Input("insert")
    SubscribableChannel receiveInsertMessage();

    @Input("update")
    SubscribableChannel receiveUpdateMessage();

    @Input("delete")
    SubscribableChannel receiveDeleteMessage();

    @Input("error")
    SubscribableChannel receiveErrorMessage();
}
