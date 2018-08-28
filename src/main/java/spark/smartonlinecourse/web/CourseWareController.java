package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.CourseWareService;

import javax.servlet.http.HttpSession;

/**
 * @ClassName CourseWareController
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/24 13:27
 * @Version 1.0
 **/
@Controller
public class CourseWareController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseWareService courseWareService;

    @GetMapping("/course_ware/{course_id}")
    public String courseWare(@PathVariable(name="course_id") Integer courseId, HttpSession session, Model model){
        User user= (User) session.getAttribute("user");
        Course course=courseService.selecCoursetByCourseId(courseId);
        Boolean ownFlag=courseService.ownCourse(courseId,user.getUserId());
        course.setOwnFlag(ownFlag);
        model.addAttribute("current_course",course);
        return "Courseware";
    }

    @ResponseBody
    @PostMapping("/course_ware_list/{course_id}")
    public String courseWareList(@PathVariable(name="course_id") Integer courseId,
                                 @RequestParam Integer page, @RequestParam Integer rows){
        String json=courseWareService.courseWareListToJson(courseId,page,rows);
        return json;
    }

    @PostMapping("/course_ware_upload/{course_id}")
    public String courseWareUpload(@PathVariable (name="course_id") Integer courseId,
                                   @RequestParam("title") String title,@RequestParam("batch") Integer batch,
                                   @RequestParam("file") MultipartFile file,HttpSession session,Model model
                                   ){
        Boolean status=courseWareService.courseWareUpload(courseId, title, batch, file);
        if(status){
            return courseWare(courseId, session, model);
        }else{
            throw new RuntimeException("上传失败:" );
        }
    }
}
