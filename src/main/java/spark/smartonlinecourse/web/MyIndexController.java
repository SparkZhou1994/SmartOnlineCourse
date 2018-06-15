package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyIndexController {
    @Autowired
    private ChooseCourseService chooseCourseService;

    @GetMapping("/my_index")
    public String myIndex(HttpSession session){
        User user= (User) session.getAttribute("user");
        Integer page = (Integer) session.getAttribute("page");
        Integer start=page*4;
        Map<String,Object> map=new HashMap<String,Object>();
        map=chooseCourseService.chooseCourseLoad(user.getUserId(),start);
        int chooseCourseCount= (int) map.get("chooseCourseCount");
        ArrayList<ChooseCourse> chooseCourseList= (ArrayList<ChooseCourse>) map.get("chooseCourseList");
        user.setChooseCourseList(chooseCourseList);
        session.setAttribute("user",user);
        session.setAttribute("chooseCourseCount",chooseCourseCount);
        return "MyIndex";
    }
}
