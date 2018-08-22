package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginAndRegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/login_and_register")
    public String loginAndRegister(){
        return "LoginAndRegister";
    }

    @PostMapping(value="/login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session, HttpServletRequest request){
        User user=new User(password,email);
        User userResult= userService.loginComfirm(user);
        session.setAttribute("user",userResult);
        session.setAttribute("index_page",0);
        return "redirect:/my_index";
    }
}
