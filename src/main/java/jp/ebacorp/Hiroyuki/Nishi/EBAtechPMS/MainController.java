package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.PageData.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    PageRepository pageRepository;

    @GetMapping("/")
    String indexController(Model model){
        model.addAttribute("title","EBAtecPMS");

        System.out.println("index is called");
        System.out.println();

        return "index";
    }

    @GetMapping("/register")
    String registerController(Model model){
        model.addAttribute("title","会員登録 | EBAtecPMS");

        System.out.println("register is called");
        System.out.println();

        return "register";
    }
}
