package spark.smartonlinecourse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @ClassName MyInforController
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/15 21:49
 * @Version 1.0
 **/
@Controller
public class MyInforController {
    @Autowired
    UserService userService;

    @GetMapping("/my_infor")
    public String myInfor(){
        return "MyInfor";
    }

    @PostMapping("/user_update")
    public String userAvatarUpload(@RequestParam("file") MultipartFile file, HttpSession session,
                                   @RequestParam("username") String userName,
                                   @RequestParam("tel") String tel,
                                   @RequestParam("email") String email, HttpServletRequest request){
        User user=(User)session.getAttribute("user");
        user.setUserName(userName);
        user.setTel(tel);
        user.setEmail(email);
        user=userService.updateUserInfo(user,file,request);
        session.setAttribute("user",user);
        return "MyInfor";
    }
}
