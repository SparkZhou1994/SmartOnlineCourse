package spark.smartonlinecourse.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * @ClassName HomeworkController
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/29 21:23
 * @Version 1.0
 **/
public class HomeworkController {
    @GetMapping("/homework/{course_id}")
    public String homework(@PathVariable(name="course_id") Integer courseId, HttpSession session){
        return "Homework";
    }
}
