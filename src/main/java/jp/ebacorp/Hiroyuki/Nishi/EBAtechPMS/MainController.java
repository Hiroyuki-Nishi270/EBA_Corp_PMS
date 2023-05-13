package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    String indexController(Model model){
        model.addAttribute("title","EBAtechPMS");

        System.out.println("index is called");
        return "index";
    }
}
