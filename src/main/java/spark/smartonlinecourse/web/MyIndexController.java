package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.ChooseCourseService;
import spark.smartonlinecourse.service.UserService;

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
    private UserService userService;

    @Autowired
    private ChooseCourseService chooseCourseService;

    @RequestMapping(value = "/my_index",method = {RequestMethod.POST,RequestMethod.GET})
    public String myIndex(User user,HttpSession session){
        user=userService.selectByEmail(user.getUsername());
        Integer page = (Integer) session.getAttribute("index_page");
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

    @RequestMapping(value = "/my_index_return",method = {RequestMethod.POST,RequestMethod.GET})
    public String myIndexReturn(HttpSession session){
        User user =(User)session.getAttribute("user");
        Integer page = (Integer) session.getAttribute("index_page");
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
        Integer page = (Integer) session.getAttribute("index_page")+1;
        Integer start=page*4;
        Map<String,Object> map=new HashMap<String,Object>();
        map=chooseCourseService.chooseCourseLoad(user.getUserId(),start);
        int chooseCourseCount= (int) map.get("chooseCourseCount");
        ArrayList<ChooseCourse> chooseCourseList= (ArrayList<ChooseCourse>) map.get("chooseCourseList");
        user.setChooseCourseList(chooseCourseList);
        session.setAttribute("user",user);
        session.setAttribute("chooseCourseCount",chooseCourseCount);
        session.setAttribute("index_page",page);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/my_index_pre_page")
    public void prePage(HttpSession session,HttpServletResponse response){
        User user= (User) session.getAttribute("user");
        Integer page = (Integer) session.getAttribute("index_page")-1;
        Integer start=page*4;
        Map<String,Object> map=new HashMap<String,Object>();
        map=chooseCourseService.chooseCourseLoad(user.getUserId(),start);
        int chooseCourseCount= (int) map.get("chooseCourseCount");
        ArrayList<ChooseCourse> chooseCourseList= (ArrayList<ChooseCourse>) map.get("chooseCourseList");
        user.setChooseCourseList(chooseCourseList);
        session.setAttribute("user",user);
        session.setAttribute("chooseCourseCount",chooseCourseCount);
        session.setAttribute("index_page",page);
        try {
            response.getWriter().write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value={"/index","/index.html"})
    public String index(HttpSession session) {
        session.removeAttribute("user");
        session.setAttribute("index_page",0);
        session.setAttribute("discuss_page", 0);
        session.setAttribute("discuss_content_page",0 );
        return "Index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session,HttpServletResponse response,HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }
}
