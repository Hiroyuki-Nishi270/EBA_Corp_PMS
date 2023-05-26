package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Service
public class addMemberService {

    @Autowired
    Properties properties;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserAction userAction;

    @Autowired
    inputFormValidator inputFormValidator;
    @InitBinder("registerForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(inputFormValidator);
    }

    public String execute(@Validated inputForm form,
                          BindingResult bindingResult,
                          Model model){

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

