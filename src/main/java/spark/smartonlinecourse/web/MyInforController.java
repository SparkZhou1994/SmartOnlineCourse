package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName MyInforController
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/15 21:49
 * @Version 1.0
 **/
@Controller
public class MyInforController {

    @GetMapping("/my_infor")
    public String myInfor(){
        return "MyInfor";
    }
}
