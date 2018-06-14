package spark.smartonlinecourse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping(value={"/index","/index.html"})
    public String index() {
        return "Index";
    }
}
