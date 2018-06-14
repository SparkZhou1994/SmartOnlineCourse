package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CoursePackageController {
    @GetMapping("/course_package")
    public String coursePackage(){
        return "CoursePackage";
    }
}
