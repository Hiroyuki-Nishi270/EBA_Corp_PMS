package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

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
        if(name.equals("register")){
            model.addAttribute("title","会員登録 | EBAtecPMS");
        }else{
            model.addAttribute("title","EBAtecPMS");
        }
        return name;
    }

    //"/"の先に文字列がないアドレスに飛んだ時用
    @GetMapping("/")
    String jumpToIndex(){
        return "redirect:index";
    }


    /**
     * 会員登録のエンドポイント
     */
    @PostMapping("register")
    String registerMapping (@Validated inputForm form,
                       BindingResult bindingResult,
                       Model model){
        if(bindingResult.hasErrors()){//registerFormのバリデーションで不合格
            return "register";
        } else {//バリデーションで合格
            return addMemberService.execute(form, model);
        }
    }

}
