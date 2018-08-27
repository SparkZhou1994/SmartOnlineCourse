package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Page;
import spark.smartonlinecourse.entity.Sign;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.SignService;

import javax.servlet.http.HttpSession;
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
        Boolean ownFlag=courseService.ownCourse(courseId,user.getUserId());
        course.setOwnFlag(ownFlag);
        model.addAttribute("current_course",course);
        return "Sign";
    }

/*    @ResponseBody
    @PostMapping("/sign_release/{course_id}")
    public String signRelease(@PathVariable("course_id") Integer courseId, @RequestParam("sign_code") String code,
                              @RequestParam("effective_minute") Integer effectiveMinute, RedirectAttributes model,HttpSession session){
        Boolean flag=signService.releaseSign(courseId,code,effectiveMinute*60);
        String status=null;
        if(flag==Boolean.TRUE){
            status="签到发布成功";
        }else{
            status="签到发布失败";
        }
        return status;
    }*/

    @ResponseBody
    @PostMapping("/sign_release")
    public String signRelease(Integer courseId,String code,Integer effectiveMinute,HttpSession session){
        User user=(User)session.getAttribute("user");
        Boolean flag=signService.releaseSign(courseId,code,effectiveMinute*60,user.getUserId());
        String status=null;
        if(flag==Boolean.TRUE){
            status="签到发布成功";
        }else{
            status="签到发布失败";
        }
        return status;
    }

/*    @ResponseBody
    @PostMapping("/sign_in/{course_id}")
    public String signIn(@PathVariable("course_id") Integer courseId,@RequestParam("user_id") Integer userId,
                         @RequestParam("sign_code") String code,RedirectAttributes model,HttpSession session){
        Boolean flag=signService.signIn(courseId,userId,code);
        String status=null;
        if(flag==Boolean.TRUE){
            status="签到成功";
        }else{
            status="签到失败";
        }

        return status;
    }*/

    @ResponseBody
    @PostMapping("/sign_in")
    public String signIn(Integer courseId,Integer userId,
                         String code){
        Boolean flag=signService.signIn(courseId,userId,code);
        String status=null;
        if(flag==Boolean.TRUE){
            status="签到成功";
        }else{
            status="签到失败";
        }
        return status;
    }

    @ResponseBody
    @PostMapping("/sign_list/{course_id}/{own_flag}")
    public String signList(@PathVariable(name="course_id") Integer courseId,@PathVariable(name="own_flag")Boolean ownFlag,
                           HttpSession session,@RequestParam Integer page,@RequestParam Integer rows){
        User user=(User)session.getAttribute("user");
        String temp =signService.signListToJson(courseId,user.getUserId(),page,rows,ownFlag);
        return temp;
    }
}
