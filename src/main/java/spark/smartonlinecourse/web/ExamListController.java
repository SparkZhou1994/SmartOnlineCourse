package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExamListController {
    @GetMapping("/exam_list")
    public String examList(){
        return "ExamList";
    }
}
