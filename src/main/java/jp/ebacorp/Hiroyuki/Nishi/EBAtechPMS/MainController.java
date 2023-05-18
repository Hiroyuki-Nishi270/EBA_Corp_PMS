package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;


@Controller
public class MainController {

    //@Autowired
    //UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @GetMapping("/")
    String indexController(Model model){
        model.addAttribute("title","EBAtecPMS");

        System.out.println("indexController is called");

        return "index";
    }

    @GetMapping("register")
    String registerController(Model model){
        model.addAttribute("title","会員登録 | EBAtecPMS");

        System.out.println("registerController is called");

        return "register";
    }

    @PostMapping("addmember")
    String addMemberController(
            Model model,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2
            ){
        if(password1.equals(password2)){
            try {
                UserDetails s = userDetailsService.loadUserByUsername(username);

                model.addAttribute("title","会員登録 | EBAtecPMS");
                model.addAttribute("result","このメールアドレスは既に登録されております");
                model.addAttribute("username",username);
                model.addAttribute("email",email);
                return "register";

            }catch (UsernameNotFoundException e){
                UserDetails user = User.builder().username(username).password(password1).roles("USER").build();
                JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

                users.createUser(user);
                System.out.println("test");

                model.addAttribute("result","会員登録に成功しました！");
                model.addAttribute("title","EBAtecPMS");
                return "index";
            }
        }else{
            model.addAttribute("title","会員登録 | EBAtecPMS");
            model.addAttribute("result","入力されたパスワードが一致しておりません");
            model.addAttribute("username",username);
            model.addAttribute("email",email);
            return "register";
        }
    }
}
