package spark.course.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName PageController
 * @Description TODO
 * @Author Spark
 * @Date 2/5/2019 8:25 PM
 * @Version 1.0
 **/
@Controller
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class PageController {
    @GetMapping("/LoginAndRegister.html")
    public String loginAndResiger() {
        return "LoginAndRegister";
    }
    @GetMapping("/Index.html")
    public String index() {
        return "Index";
    }
}
