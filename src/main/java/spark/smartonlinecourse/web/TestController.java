package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 16:12
 * @Version 1.0
 **/
@Controller
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "Test";
    }
}
