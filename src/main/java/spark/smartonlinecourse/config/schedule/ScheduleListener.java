package spark.smartonlinecourse.config.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import spark.smartonlinecourse.schedule.ScheduleService;

/**
 * @ClassName ScheduleListener
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/18 10:27
 * @Version 1.0
 **/
@Component
public class ScheduleListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            scheduleService.homeworkMessageSchedule();
            scheduleService.signMessageSchedule();
            scheduleService.courseWareMessageSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
