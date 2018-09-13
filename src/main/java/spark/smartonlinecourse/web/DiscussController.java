package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Discuss;
import spark.smartonlinecourse.entity.DiscussContent;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.service.DiscussContentService;
import spark.smartonlinecourse.service.DiscussService;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        if(totalPage ==0){
            totalPage=1;
        }
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
        if(totalPage ==0){
            totalPage=1;
        }
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
        if(totalPage ==0){
            totalPage=1;
        }
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
        model.addAttribute("discuss",discuss);
        if(discuss.getVote()=='1'){
            Integer choose1Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '1');
            Integer choose2Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '2');
            Integer choose3Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'3');
            Integer choose4Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'4' );
            model.addAttribute("choose1_count",choose1Count);
            model.addAttribute("choose2_count",choose2Count);
            model.addAttribute("choose3_count",choose3Count);
            model.addAttribute("choose4_count",choose4Count);
            model.addAttribute("vote", 1);
        }else{
            Integer page=(Integer) session.getAttribute("discuss_content_page");
            List<DiscussContent> discussContentList=discussContentService.selectByDiscussIdAndPage(discussId, page);
            Integer totalPage=(int) Math.ceil((double)discussContentCount/10);
            if(totalPage ==0){
                totalPage=1;
            }
            model.addAttribute("total_page",totalPage);
            model.addAttribute("discuss_content_list",discussContentList);
            model.addAttribute("vote",0);
        }
        Course course=courserService.selectCourseByCourseId(discuss.getCourseId());
        model.addAttribute("current_course",course);
        return "DiscussContent";
    }

    @PostMapping("/discuss_content_insert/{discuss_id}")
    public String discussContentInsert(@PathVariable("discuss_id") Integer discussId,DiscussContent discussContent, HttpSession session, HttpServletResponse response,Model model){
        User user=(User)session.getAttribute("user");
        discussContent.setUserId(user.getUserId());
        discussContent.setDiscussId(discussId);
        Boolean status=discussContentService.insertDiscussContent(discussContent);
        if(status==true){
            return discussContent(discussId,session,model);
        }else{
            throw new RuntimeException("发布失败");
        }
    }

    @PostMapping("/discuss_content_next_page/{discuss_id}")
    public String discussContentNextPage(@PathVariable("discuss_id") Integer discussId,Model model,HttpSession session){
        Integer discussContentCount=discussContentService.selectAllCountByDiscussId(discussId);
        model.addAttribute("discuss_content_count",discussContentCount);
        Discuss discuss=discussService.selectByDiscussId(discussId);
        model.addAttribute("discuss",discuss);
        if(discuss.getVote()=='1'){
            Integer choose1Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '1');
            Integer choose2Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '2');
            Integer choose3Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'3');
            Integer choose4Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'4' );
            model.addAttribute("choose1_count",choose1Count);
            model.addAttribute("choose2_count",choose2Count);
            model.addAttribute("choose3_count",choose3Count);
            model.addAttribute("choose4_count",choose4Count);
            model.addAttribute("vote", 1);
        }else{
            Integer page=(Integer) session.getAttribute("discuss_content_page");
            page+=1;
            session.setAttribute("discuss_content_page",page);
            List<DiscussContent> discussContentList=discussContentService.selectByDiscussIdAndPage(discussId, page);
            Integer totalPage=(int) Math.ceil((double)discussContentCount/10);
            if(totalPage ==0){
                totalPage=1;
            }
            model.addAttribute("total_page",totalPage);
            model.addAttribute("discuss_content_list",discussContentList);
            model.addAttribute("vote",0);
        }
        Course course=courserService.selectCourseByCourseId(discuss.getCourseId());
        model.addAttribute("current_course",course);
        return "DiscussContent";
    }

    @PostMapping("/discuss_content_pre_page/{discuss_id}")
    public String discussContentPrePage(@PathVariable("discuss_id") Integer discussId,HttpSession session,Model model){
        Integer discussContentCount=discussContentService.selectAllCountByDiscussId(discussId);
        model.addAttribute("discuss_content_count",discussContentCount);
        Discuss discuss=discussService.selectByDiscussId(discussId);
        model.addAttribute("discuss",discuss);
        if(discuss.getVote()=='1'){
            Integer choose1Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '1');
            Integer choose2Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId, '2');
            Integer choose3Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'3');
            Integer choose4Count=discussContentService.selectChooseCountByDiscussIdAndChoose(discussId,'4' );
            model.addAttribute("choose1_count",choose1Count);
            model.addAttribute("choose2_count",choose2Count);
            model.addAttribute("choose3_count",choose3Count);
            model.addAttribute("choose4_count",choose4Count);
            model.addAttribute("vote", 1);
        }else{
            Integer page=(Integer) session.getAttribute("discuss_content_page");
            page-=1;
            session.setAttribute("discuss_content_page",page);
            List<DiscussContent> discussContentList=discussContentService.selectByDiscussIdAndPage(discussId, page);
            Integer totalPage=(int) Math.ceil((double)discussContentCount/10);
            if(totalPage ==0){
                totalPage=1;
            }
            model.addAttribute("total_page",totalPage);
            model.addAttribute("discuss_content_list",discussContentList);
            model.addAttribute("vote",0);
        }
        Course course=courserService.selectCourseByCourseId(discuss.getCourseId());
        model.addAttribute("current_course",course);
        return "DiscussContent";
    }
}
