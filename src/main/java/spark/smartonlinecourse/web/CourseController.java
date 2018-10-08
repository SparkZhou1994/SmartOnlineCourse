package spark.smartonlinecourse.web;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.CourseService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "redirect:/my_index_return";
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
        return "redirect:/my_index_return";
    }

    @PostMapping("/course_score")
    public void courseScore(Integer score,Integer courseId,HttpServletResponse response,HttpSession session){
        User user=(User)session.getAttribute("user");
        ChooseCourse chooseCourse=new ChooseCourse();
        chooseCourse.setUserId(user.getUserId());
        chooseCourse.setCourseId(courseId);
        chooseCourse.setScore((byte)score.intValue());
        Boolean status=courseService.updateScore(chooseCourse);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/course_search")
    public void courseSearch(String courseName,HttpSession session,HttpServletResponse response,HttpServletRequest request){
        List<Course> courseList=courseService.selectCourseByCourseName(courseName);
        request.setAttribute("courseList",courseList);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
