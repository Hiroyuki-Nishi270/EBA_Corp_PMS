package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    //registerForm用form-backing bean
    @ModelAttribute
    public registerForm setUpRegisterForm(){
        return new registerForm();
    }

    //registerForm用のValidator
    @Autowired
    registerFormValidator registerFormValidator;

    @InitBinder("registerForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(registerFormValidator);
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserAction userAction;

    @GetMapping("/")
    String indexController(Model model){
        model.addAttribute("title","EBAtecPMS");
        return "index";
    }

    @GetMapping("register")
    String registerController(Model model){
        model.addAttribute("title","会員登録 | EBAtecPMS");
        return "register";
    }

    @PostMapping("register")
    String addMemberController(@Validated registerForm form,
                               BindingResult bindingResult,
                               Model model
            ){
        if(bindingResult.hasErrors()){//registerFormのバリデーション
            return "register";
        } else {
            try {
                UserDetails s = userDetailsService.loadUserByUsername(form.getUsername());

                model.addAttribute("title","会員登録 | EBAtecPMS");
                model.addAttribute("result","このユーザーは既に登録されております");
                model.addAttribute("username",form.getUsername());
                //model.addAttribute("email",email);
                return "register";

            }catch (UsernameNotFoundException e){
                userAction.createUser(form.getUsername(), form.getPassword1());

                model.addAttribute("result","会員登録に成功しました！");
                model.addAttribute("title","EBAtecPMS");
                return "index";
            }
        }
    }
}
