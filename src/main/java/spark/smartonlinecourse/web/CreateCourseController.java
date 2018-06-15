package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName CreateCourseController
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/15 18:13
 * @Version 1.0
 **/
@Controller
public class CreateCourseController {

    @GetMapping("/create_course")
    public String createCourse(){
        return "CreateCourse";
    }
}
