package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TaskListController {
    @GetMapping("/task_list")
    public String taskList(){
        return "TaskList";
    }
}
