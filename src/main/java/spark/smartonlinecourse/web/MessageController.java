package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Message;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.MessageService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName MessageController
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/18 13:10
 * @Version 1.0
 **/
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    ChooseCourseService chooseCourseService;

    @Autowired
    CourseService courseService;

    @GetMapping("/message/{course_id}")
    public String message(@PathVariable("course_id")Integer courseId, HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        Key key=new Key();
        key.setCourseId(courseId);
        key.setUserId(user.getUserId());
        List<Integer> chooseCourseIdList = chooseCourseService.selectChooseCourseId(key);
        List<Message> messageList=messageService.selectByChooseCouseId(chooseCourseIdList.get(0));
        model.addAttribute("message_list",messageList);
        Course course=courseService.selectCourseByCourseId(courseId);
        model.addAttribute("current_course",course);
        return "Message";
    }
}
