package spark.course.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName ScheduleListener
 * @Description TODO
 * @Author Spark
 * @Date 2/13/2019 12:04 PM
 * @Version 1.0
 **/
public class ScheduleListener implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            scheduleService.userAgeSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
