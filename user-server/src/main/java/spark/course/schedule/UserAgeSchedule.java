package spark.course.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import spark.course.service.UserService;

/**
 * @ClassName UserAgeSchedule
 * @Description TODO
 * @Author Spark
 * @Date 2/13/2019 12:25 PM
 * @Version 1.0
 **/
public class UserAgeSchedule extends QuartzJobBean {
    @Autowired
    UserService userService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        userService.updateAge();
    }
}
