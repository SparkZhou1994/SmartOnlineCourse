package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.UserService;
import spark.smartonlinecourse.util.PasswordEncord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginAndRegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/login_and_register",method ={RequestMethod.POST,RequestMethod.GET})
    public String loginAndRegister(@RequestParam(value = "error", required = false) String error,HttpSession session){
        session.setAttribute("index_page",0);
        session.setAttribute("discuss_page", 0);
        session.setAttribute("discuss_content_page",0 );
        return "LoginAndRegister";
    }

    @PostMapping(value="/login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session, HttpServletRequest request){
        User user=new User(password,email);
        User userResult= userService.loginComfirm(user);
        session.setAttribute("user",userResult);
        session.setAttribute("index_page",0);
        session.setAttribute("discuss_page", 0);
        session.setAttribute("discuss_content_page",0 );
        return "redirect:/my_index";
    }

    @PostMapping("/sign_up")
    public String signIn(User user){
        String passwordEncord=PasswordEncord.passwordEncord(user.getPassword());
        user.setPassword(passwordEncord);
        Boolean status=userService.signUp(user);
        if(status){
            return "redirect:/my_index";
        }else{
            return "LoginAndRegister";
        }
    }
}
