package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.SignService;

import javax.servlet.http.HttpSession;

/**
 * @ClassName SignController
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/21 21:06
 * @Version 1.0
 **/
@Controller
public class SignController {
    @Autowired
    ChooseCourseService chooseCourseService;
    @Autowired
    SignService signService;

    @GetMapping("/sign/{course_id}")
    public String sign(@PathVariable(name="course_id")Integer courseId, HttpSession session, Model model){
        User user= (User) session.getAttribute("user");
        Boolean ownFlag=chooseCourseService.ownCourse(courseId,user.getUserId());
        session.setAttribute("own_flag",ownFlag);
        model.addAttribute("current_course_id",courseId);
        return "Sign";
    }

    @PostMapping("/sign_release")
    public String signRelease(@RequestParam("course_id") Integer courseId,String code,
                              @RequestParam("effective_minute") Integer effectiveMinute, Model model){
        Boolean flag=signService.releaseSign(courseId,code,effectiveMinute*60);
        model.addAttribute("sign_status",flag);
        return "Sign";
    }
    @PostMapping("/sign/")
    public String signIn(@RequestParam("course_id") Integer courseId,@RequestParam("user_id") Integer userId,String code,Model model){
        Boolean flag=signService.signIn(courseId,userId,code);
        model.addAttribute("sign_status",flag);
        return "Sign";
    }
}
