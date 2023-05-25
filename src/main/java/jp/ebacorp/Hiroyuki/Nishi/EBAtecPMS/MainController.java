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
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;


@Controller
public class MainController {

    //プロパティ呼び出し
    @Autowired
    Properties properties;

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

    @GetMapping("{name}")
    String getMapping(@PathVariable String name, Model model){
        System.out.println("name is "+name);
        if(name.equals(properties.getPageRegister())){
            model.addAttribute(properties.getTitleName(),properties.getRegisterTitle());
            return name;
        }else{
            model.addAttribute(properties.getTitleName(),properties.getDefaultTitle());
            return name;
        }
    }

    //"/"の先に文字列がないアドレスに飛んだ時用(妥協)
    @GetMapping("/")
    String jumpToIndex(){
        return "redirect:index";
    }

    @PostMapping("{name}")
    String postMapping(@PathVariable String name,
                       @Validated registerForm form,
                       BindingResult bindingResult,
                       Model model){
        if(name.equals(properties.getPageRegister())){


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
        return null;
    }
    /**
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
    }**/
}
