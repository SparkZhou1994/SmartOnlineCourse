package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @GetMapping("/my_index_next_page")
    public void nextPage(HttpSession session, HttpServletResponse response){
        User user= (User) session.getAttribute("user");
        Integer page = (Integer) session.getAttribute("page")+1;
        Integer start=page*4;
        Map<String,Object> map=new HashMap<String,Object>();
        map=chooseCourseService.chooseCourseLoad(user.getUserId(),start);
        int chooseCourseCount= (int) map.get("chooseCourseCount");
        ArrayList<ChooseCourse> chooseCourseList= (ArrayList<ChooseCourse>) map.get("chooseCourseList");
        user.setChooseCourseList(chooseCourseList);
        session.setAttribute("user",user);
        session.setAttribute("chooseCourseCount",chooseCourseCount);
        session.setAttribute("page",page);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/my_index_pre_page")
    public void prePage(HttpSession session,HttpServletResponse response){
        User user= (User) session.getAttribute("user");
        Integer page = (Integer) session.getAttribute("page")-1;
        Integer start=page*4;
        Map<String,Object> map=new HashMap<String,Object>();
        map=chooseCourseService.chooseCourseLoad(user.getUserId(),start);
        int chooseCourseCount= (int) map.get("chooseCourseCount");
        ArrayList<ChooseCourse> chooseCourseList= (ArrayList<ChooseCourse>) map.get("chooseCourseList");
        user.setChooseCourseList(chooseCourseList);
        session.setAttribute("user",user);
        session.setAttribute("chooseCourseCount",chooseCourseCount);
        session.setAttribute("page",page);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value={"/index","/index.html"})
    public String index() {
        return "Index";
    }
}
