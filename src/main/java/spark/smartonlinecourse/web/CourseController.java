package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.SignService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author Spark
 * @Date 2018/7/17 15:45
 * @Version 1.0
 **/
@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    ChooseCourseService chooseCourseService;

    @PostMapping("/course_add")
    public String addCourse(@RequestParam("file") MultipartFile file, HttpSession session, @RequestParam("course_name") String courseName, @RequestParam("catalog") String catalog, HttpServletRequest request){
        Course course=new Course();
        User user=(User)session.getAttribute("user");
        course.setUserId(user.getUserId());
        course.setUserName(user.getUserName());
        course.setCourseName(courseName);
        course.setCatalog(catalog);
        course=courseService.insertCourse(course,file,request);
        return "MyIndex";
    }

    @GetMapping("/create_course")
    public String createCourse(){
        return "CreateCourse";
    }

    @GetMapping("/course_package")
    public String coursePackage(HttpServletRequest request, HttpServletResponse response){
        Integer start=0;
        ArrayList<Course> courseList= (ArrayList<Course>) courseService.selectTop(start);
        request.setAttribute("courseList",courseList);
        return "CoursePackage";
    }

    @GetMapping("/join_course")
    public String joinCourse(){
        return "JoinCourse";
    }

    @PostMapping("/join_course_deal")
    public String joinCourseDeal(HttpSession session, @RequestParam("course_id") Integer courseId){
        User user=(User)session.getAttribute("user");
        chooseCourseService.joinCourse(courseId,user.getUserId());
        return "redirect:/my_index";
    }

    @GetMapping("/course_ware/{course_id}")
    public String courseWare(@PathVariable(name="course_id") Integer courseId,HttpSession session,Model model){
        User user= (User) session.getAttribute("user");
        Boolean ownFlag=chooseCourseService.ownCourse(courseId,user.getUserId());
        session.setAttribute("own_flag",ownFlag);
        model.addAttribute("current_course_id",courseId);
        return "Courseware";
    }
    @GetMapping("/discuss/{course_id}")
    public String discuss(@PathVariable(name="course_id") Integer courseId){
        return "Discuss";
    }
    @GetMapping("/homework/{course_id}")
    public String homework(@PathVariable(name="course_id") Integer courseId,HttpSession session){
        return "Homework";
    }
    @GetMapping("/message/{course_id}")
    public String message(@PathVariable(name="course_id") Integer courseId){
        return "Message";
    }
}
