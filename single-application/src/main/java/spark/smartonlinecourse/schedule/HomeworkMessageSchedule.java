package spark.smartonlinecourse.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import spark.smartonlinecourse.service.MessageService;

/**
 * @ClassName HomeworkMessageSchedule
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/18 10:12
 * @Version 1.0
 **/
public class HomeworkMessageSchedule extends QuartzJobBean {

    @Autowired
    MessageService messageService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        messageService.homeworkMessage();
    }
}