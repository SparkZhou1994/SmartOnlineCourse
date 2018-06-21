package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class CoursePackageController {
    @Autowired
    CourseService courseService;

    @GetMapping("/course_package")
    public String coursePackage(HttpServletRequest request, HttpServletResponse response){
        Integer start=0;
        ArrayList<Course> courseList= (ArrayList<Course>) courseService.selectTop(start);
        request.setAttribute("courseList",courseList);
        return "CoursePackage";
    }
}
