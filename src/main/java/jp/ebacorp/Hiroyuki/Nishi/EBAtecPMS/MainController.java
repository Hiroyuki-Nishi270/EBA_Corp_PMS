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


@Controller
public class MainController {

    //プロパティ呼び出し
    @Autowired
    Properties properties;

    @Autowired
    addMemberService addMemberService;

    //registerForm用form-backing bean
    @ModelAttribute
    public inputForm setUpRegisterForm(){
        return new inputForm();
    }

    @GetMapping("{name}")
    String getMapping(@PathVariable String name, Model model){
        System.out.println("name is "+name);
        if(name.equals(properties.getPageRegister())){
            model.addAttribute(properties.getTitleName(),properties.getRegisterTitle());
        }else{
            model.addAttribute(properties.getTitleName(),properties.getDefaultTitle());
        }
        return name;
    }

    //"/"の先に文字列がないアドレスに飛んだ時用(妥協)
    @GetMapping("/")
    String jumpToIndex(){
        return "redirect:index";
    }

    @PostMapping("{name}")
    String postMapping(@PathVariable String name,
                       @Validated inputForm form,
                       BindingResult bindingResult,
                       Model model){
        if(name.equals(properties.getPageRegister())){
            //会員登録処理
            if(bindingResult.hasErrors()){//registerFormのバリデーションで不合格
                return properties.getPageRegister();
            } else {//バリデーションで合格
                return addMemberService.execute(form, model);
            }
        }
        return properties.getPageDefault();
    }

}
