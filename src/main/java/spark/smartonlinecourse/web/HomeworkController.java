package spark.smartonlinecourse.web;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.HomeworkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName HomeworkController
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/29 21:23
 * @Version 1.0
 **/
@Controller
public class HomeworkController {

    @Autowired
    CourseService courseService;

    @Autowired
    HomeworkService homeworkService;

    @GetMapping("/homework/{course_id}")
    public String homework(@PathVariable(name="course_id") Integer courseId,HttpSession session, Model model){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now=dateTimeFormatter.format(LocalDateTime.now());
        model.addAttribute("now",now);
        Course course=courseService.selectCourseByCourseId(courseId);
        User user= (User) session.getAttribute("user");
        Boolean ownFlag=courseService.ownCourse(courseId,user.getUserId());
        course.setOwnFlag(ownFlag);
        model.addAttribute("current_course",course);
        return "Homework";
    }

    @ResponseBody
    @PostMapping("/homework_list/{course_id}")
    public String homeworkList(@PathVariable("course_id") Integer courseId, HttpSession session,
                           @RequestParam Integer page, @RequestParam Integer rows){
        User user=(User)session.getAttribute("user");
        Integer userId=user.getUserId();
        String result=homeworkService.homeworkListToJson(courseId,userId,page,rows);
        return result;
    }

    @PostMapping("/homework_release/{course_id}")
    public String homeworkRelease(@PathVariable("course_id") Integer courseId, HttpSession session, Model model,
                                  String title, String describe, MultipartFile file, @RequestParam("end_time") String endTime,Integer batch){
        User user=(User)session.getAttribute("user");
        Integer userId=user.getUserId();
        Boolean status=homeworkService.homeworkRelease(title,describe,file,courseId,endTime,batch,userId);
        if(status){
            return homework(courseId,session,model);
        }else{
            throw new RuntimeException("发布失败！" );
        }
    }

    @GetMapping("/homework_download/{homework_id}")
    public ResponseEntity<byte[]> homeworkDownload(@PathVariable ("homework_id") Integer homeworkId, HttpServletRequest request){
        ResponseEntity<byte[]> bytes=homeworkService.homeworkDownload(request,homeworkId);
        return bytes;
    }

    @PostMapping("/homework_upload/{course_id}/{homework_id}")
    public String homeworkUpload(@PathVariable ("course_id") Integer courseId,@PathVariable("homework_id") Integer homeworkId,HttpSession session,Model model,
                                 MultipartFile file){
        Boolean status=homeworkService.homeworkUpload(homeworkId,file,courseId);
        if(status){
            /*return "Success";*/
            return homework(courseId,session,model);
        }else{
            throw new RuntimeException("上传失败！");
        }
    }

    @PostMapping("/homework_evaluate")
    @ResponseBody
    public Boolean homeworkEvaluate(Integer homeworkId, Integer score, HttpSession session, Model model){
        System.out.print("1");
        Boolean status=homeworkService.homeworkEvaluate(homeworkId,score);
        if(status){
            return status;
        }else{
            throw new RuntimeException("评分失败！");
        }
    }
}
