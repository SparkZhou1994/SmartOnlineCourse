package spark.smartonlinecourse.web;

import com.sun.xml.internal.ws.resources.WsdlmodelMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spark.smartonlinecourse.entity.Discuss;
import spark.smartonlinecourse.entity.User;
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

    @GetMapping("/discuss/{course_id}")
    public String discuss(@PathVariable(name="course_id") Integer courseId, HttpSession session,Model model){
        Integer page=(Integer) session.getAttribute("discuss_page");
        List<Discuss> discussList=discussService.selectByCourseIdAndPage(courseId,page);
        Integer discussCount=discussService.selectCountByCourseId(courseId);
        Integer totalPage=(int) Math.ceil((double)discussCount/10);
        model.addAttribute("discuss_list",discussList);
        model.addAttribute("discuss_count",discussCount);
        model.addAttribute("totalPage",totalPage);
        return "Discuss";
    }

    @ResponseBody
    @PostMapping("/discuss_insert/{course_id}")
    public String discussInsert(Discuss discuss,@PathVariable(name="course_id") Integer courseId,HttpSession session,Model model){
        User user=(User) session.getAttribute("user");
        discuss.setUseId(user.getUserId());
        discuss.setCourseId(courseId);
        Boolean status=discussService.insertDiscuss(discuss);
        if(status){
            return "Success";
        }else{
            return "Fail";
        }
    }
}
