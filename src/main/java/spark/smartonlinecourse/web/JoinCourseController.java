package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName JoinCourseController
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/15 18:20
 * @Version 1.0
 **/
@Controller
public class JoinCourseController {

    @GetMapping("/join_course")
    public String joinCourse(){
        return "JoinCourse";
    }

}
