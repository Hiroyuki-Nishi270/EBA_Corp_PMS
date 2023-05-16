package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @Autowired
    UserService userService;

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

    @PostMapping("/addmember")
    String addMemberController(
            Model model,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2
            ){

        userService.createUser(username,email,password1);

        model.addAttribute("result","会員登録に成功しました！");

        return "index";
    }
}
