package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Discuss;
import spark.smartonlinecourse.entity.DiscussContent;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.DiscussContentService;
import spark.smartonlinecourse.service.DiscussService;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName DiscussController
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/6 12:41
 * @Version 1.0
 **/
@Controller
public class DiscussController {

    @Autowired
    DiscussService discussService;

    @Autowired
    DiscussContentService discussContentService;

    @Autowired
    CourseService courserService;

    @GetMapping("/discuss/{course_id}")
    public String discuss(@PathVariable(name="course_id") Integer courseId, HttpSession session,Model model){
        Integer page=(Integer) session.getAttribute("discuss_page");
        List<Discuss> discussList=discussService.selectByCourseIdAndPage(courseId,page);
        Integer discussCount=discussService.selectCountByCourseId(courseId);
        Integer totalPage=(int) Math.ceil((double)discussCount/10);
        Course course=courserService.selectCourseByCourseId(courseId);
        model.addAttribute("current_course",course);
        model.addAttribute("discuss_list",discussList);
        model.addAttribute("total_page",totalPage);
        return "Discuss";
    }

    @PostMapping("/discuss_insert/{course_id}")
    public String discussInsert(Discuss discuss,@PathVariable(name="course_id") Integer courseId,HttpSession session,Model model){
        User user=(User) session.getAttribute("user");
        discuss.setUserId(user.getUserId());
        discuss.setCourseId(courseId);
        Boolean status=discussService.insertDiscuss(discuss);
        if(status){
            return "redirect:/discuss/"+courseId;
        }else{
            throw new RuntimeException("操作失败！" );
        }
    }

    @PostMapping("/discuss_next_page/{course_id}")
    public String discussNextPage(@PathVariable(name="course_id") Integer courseId,HttpSession session,Model model){
        Integer page=(Integer) session.getAttribute("discuss_page");
        page=page+1;
        List<Discuss> discussList=discussService.selectByCourseIdAndPage(courseId,page);
        Integer discussCount=discussService.selectCountByCourseId(courseId);
        Integer totalPage=(int) Math.ceil((double)discussCount/10);
        Course course=courserService.selectCourseByCourseId(courseId);
        model.addAttribute("current_course",course);
        model.addAttribute("discuss_list",discussList);
        model.addAttribute("total_page",totalPage);
        session.setAttribute("discuss_page",page );
        return "Discuss";
    }

    @PostMapping("/discuss_pre_page/{course_id}")
    public String discussPrePage(@PathVariable(name="course_id") Integer courseId,HttpSession session,Model model){
        Integer page=(Integer) session.getAttribute("discuss_page");
        page=page-1;
        List<Discuss> discussList=discussService.selectByCourseIdAndPage(courseId,page);
        Integer discussCount=discussService.selectCountByCourseId(courseId);
        Integer totalPage=(int) Math.ceil((double)discussCount/10);
        Course course=courserService.selectCourseByCourseId(courseId);
        model.addAttribute("current_course",course);
        model.addAttribute("discuss_list",discussList);
        model.addAttribute("total_page",totalPage);
        session.setAttribute("discuss_page",page );
        return "Discuss";
    }

    @GetMapping("/discuss_content/{discuss_id}")
    public String discussContent(@PathVariable(name="discuss_id") Integer discussId, HttpSession session,Model model){
        Integer discussContentCount=discussContentService.selectAllCountByDiscussId(discussId);
        model.addAttribute("discuss_content_count",discussContentCount);
        Discuss discuss=discussService.selectByDiscussId(discussId);
        if(discuss.getVote()=='1'){
            Integer choose1Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '1');
            Integer choose2Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '2');
            Integer choose3Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'3');
            Integer choose4Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'4' );
            model.addAttribute("choose1Count",choose1Count);
            model.addAttribute("choose2Count",choose2Count);
            model.addAttribute("choose3Count",choose3Count);
            model.addAttribute("choose4Count",choose4Count);
        }else{
            Integer page=(Integer) session.getAttribute("discuss_content_page");
            List<DiscussContent> discussContentList=discussContentService.selectByDiscussIdAndPage(discussId, page);
            Integer totalPage=(int) Math.ceil((double)discussContentCount/10);
            model.addAttribute("total_page",totalPage);
            model.addAttribute("discuss_list",discussContentList);
        }
        Course course=courserService.selectCourseByCourseId(discuss.getCourseId());
        model.addAttribute("current_course",course);
        return "DiscussContent";
    }
}
