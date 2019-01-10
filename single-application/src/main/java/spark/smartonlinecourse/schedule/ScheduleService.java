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
    /*
     *    六个或七个单元
     * 秒 分 时 月中天 月份 月中星期几 (年)
     * 秒，分，时，天
     * 字段 允许值 允许的特殊字符
        秒 0-59 , - * /
        分 0-59 , - * /
        小时 0-23 , - * /
        日期 1-31 , - * ? / L W C
        月份 1-12 或者 JAN-DEC , - * /
        星期 1-7 或者 SUN-SAT , - * ? / L C #
        年（可选） 留空, 1970-2099 , - * /

     * 符号说明
        星号：表示任意时刻
        问号：只能在日或周字段上使用，简单的理解就是日期和星期是有冲突的，指定其中一个的话，另外一个是没办法指定的，比如每个月12号和每个星期二，这两个是"互斥"的，不能用日期和周来指定所有“每个是星期二的12号”这个时间。
        减号：范围，如 1-5秒
        逗号：列表，如 1,5,10 秒
        斜杠：等步长序列，如3/13秒 表示 3,16,29,42,55,3,16...
        L：仅在日和周上支持，表示允许的最后一个值，注意不要让范围和列表与L连用
        W：工作日
        井号：为给定月份指定具体的工作日实例。把“MON#2”放在周内日期字段中，表示把任务安排在当月的第二个星期一。
     */
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

    public void courseWareMessageSchedule() throws Exception{
        JobDetail jobDetail=JobBuilder.newJob(CourseWareMessageSchedule.class).build();
        CronScheduleBuilder scheduleBuilder= CronScheduleBuilder.cronSchedule("0 0 1 * * ?")
                .withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger=TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
