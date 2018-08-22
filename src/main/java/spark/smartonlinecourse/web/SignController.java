package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Sign;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.SignService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    CourseService courseService;

    @GetMapping("/sign/{course_id}")
    public String sign(@PathVariable(name="course_id")Integer courseId, HttpSession session, Model model){
        User user= (User) session.getAttribute("user");
        Course course=courseService.selecCoursetByCourseId(courseId);
        Boolean ownFlag=chooseCourseService.ownCourse(courseId,user.getUserId());
        course.setOwnFlag(ownFlag);
        List<Sign> signList=new ArrayList<Sign>();
        Integer signCount;
        if(ownFlag){
            signList=signService.selectSignByCourseIdAndUserIdAndStart(courseId,null,0);
            signCount=signService.selectCountByCourseIdAndUserId(courseId,null);
        }else{
            signList=signService.selectSignByCourseIdAndUserIdAndStart(courseId,user.getUserId(),0);
            signCount=signService.selectCountByCourseIdAndUserId(courseId,user.getUserId());
        }
        model.addAttribute("sign_page",0);
        model.addAttribute("sign_list",signList);
        model.addAttribute("sign_count",signCount);
        model.addAttribute("current_course",course);
        return "Sign";
    }

    @PostMapping("/sign_release")
    public String signRelease(@RequestParam("course_id") Integer courseId, String code,
                              @RequestParam("effective_minute") Integer effectiveMinute, RedirectAttributes model){
        Boolean flag=signService.releaseSign(courseId,code,effectiveMinute*60);
        model.addFlashAttribute("sign_status",flag);
        return "Sign";
    }

    @PostMapping("/sign_in/")
    public String signIn(@RequestParam("course_id") Integer courseId,@RequestParam("user_id") Integer userId,String code,RedirectAttributes model){
        Boolean flag=signService.signIn(courseId,userId,code);
        model.addFlashAttribute("sign_status",flag);
        return "Sign";
    }

    @PostMapping("/sign_next_page")
    public void signNextPage(@RequestBody Integer signPage,
                             @RequestBody Boolean ownFlag,
                             @RequestBody Integer currentCourseId,
                             HttpSession session,Model model,
                             HttpServletResponse response){
        signPage+=1;
        List<Sign> signList=new ArrayList<Sign>();
        Integer signCount;
        User user=(User)session.getAttribute("user");
        Course course=courseService.selecCoursetByCourseId(currentCourseId);
        if(ownFlag){
            signList=signService.selectSignByCourseIdAndUserIdAndStart(currentCourseId,null,signPage);
            signCount=signService.selectCountByCourseIdAndUserId(currentCourseId,null);
        }else{
            signList=signService.selectSignByCourseIdAndUserIdAndStart(currentCourseId,user.getUserId(),signPage);
            signCount=signService.selectCountByCourseIdAndUserId(currentCourseId,user.getUserId());
        }
        model.addAttribute("current_course_id",currentCourseId);
        model.addAttribute("sign_page",signPage);
        model.addAttribute("sign_list",signList);
        model.addAttribute("sign_count",signCount);
        model.addAttribute("current_course",course);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/sign_pre_page")
    public void signPrePage(@RequestBody Integer signPage,
                               @RequestBody Boolean ownFlag,
                               @RequestBody Integer currentCourseId,
                               HttpSession session,Model model,
                            HttpServletResponse response){
        signPage-=1;
        List<Sign> signList=new ArrayList<Sign>();
        Integer signCount;
        User user=(User)session.getAttribute("user");
        Course course=courseService.selecCoursetByCourseId(currentCourseId);
        if(ownFlag){
            signList=signService.selectSignByCourseIdAndUserIdAndStart(currentCourseId,null,signPage);
            signCount=signService.selectCountByCourseIdAndUserId(currentCourseId,null);
        }else{
            signList=signService.selectSignByCourseIdAndUserIdAndStart(currentCourseId,user.getUserId(),signPage);
            signCount=signService.selectCountByCourseIdAndUserId(currentCourseId,user.getUserId());
        }
        model.addAttribute("current_course_id",currentCourseId);
        model.addAttribute("sign_page",signPage);
        model.addAttribute("sign_list",signList);
        model.addAttribute("sign_count",signCount);
        model.addAttribute("current_course",course);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
