package spark.smartonlinecourse.schedule;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ScheduleService
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/18 10:07
 * @Version 1.0
 **/
@Service
public class ScheduleService {

    @Autowired
    private Scheduler scheduler;

    public void homeworkMessageSchedule() throws Exception{
        JobDetail jobDetail=JobBuilder.newJob(HomeworkMessageSchedule.class).build();
        CronScheduleBuilder scheduleBuilder= CronScheduleBuilder.cronSchedule("0 0 1 * * ?")
                .withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger=TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    public void signMessageSchedule() throws Exception{
        JobDetail jobDetail=JobBuilder.newJob(SignMessageSchedule.class).build();
        CronScheduleBuilder scheduleBuilder= CronScheduleBuilder.cronSchedule("0 0 1 * * ?")
                .withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger=TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
