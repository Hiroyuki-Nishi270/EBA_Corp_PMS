package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class addMemberService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserAction userAction;

    /**
     * バリデーションをパスした後の処理
     * @param form 会員登録のform
     * @param model model
     * @return ページ名
     *
     */
    public String execute(userRegisterForm form,
                          Model model){
        try {
            UserDetails s = userDetailsService.loadUserByUsername(form.getUsername());

            model.addAttribute("result","このユーザーは既に登録されております");
            model.addAttribute("title","会員登録 | EBAtecPMS");
            model.addAttribute("username",form.getUsername());
            return "register";

        }catch (UsernameNotFoundException e){
            userAction.createUser(form.getUsername(), form.getPassword1());

            model.addAttribute("result","会員登録に成功しました！");
            model.addAttribute("title","EBAtecPMS");
            return "index";
        }
    }

}

