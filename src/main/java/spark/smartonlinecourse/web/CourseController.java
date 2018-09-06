package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.CourseService;


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
        course=courseService.insertCourse(course,file,request,user.getUserId());
        return "MyIndex";
    }

    @GetMapping("/course_package")
    public String coursePackage(HttpServletRequest request, HttpServletResponse response){
        Integer start=0;
        ArrayList<Course> courseList= (ArrayList<Course>) courseService.selectTop(start);
        request.setAttribute("courseList",courseList);
        return "CoursePackage";
    }

    @PostMapping("/join_course_deal")
    public String joinCourseDeal(HttpSession session, @RequestParam("course_id") Integer courseId){
        User user=(User)session.getAttribute("user");
        chooseCourseService.joinCourse(courseId,user.getUserId());
        return "redirect:/my_index";
    }

    @GetMapping("/message/{course_id}")
    public String message(@PathVariable(name="course_id") Integer courseId){
        return "Message";
    }
}
