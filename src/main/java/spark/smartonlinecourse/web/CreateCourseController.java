package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName CreateCourseController
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/15 18:13
 * @Version 1.0
 **/
@Controller
public class CreateCourseController {

    @Autowired
    CourseService courseService;

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
}
