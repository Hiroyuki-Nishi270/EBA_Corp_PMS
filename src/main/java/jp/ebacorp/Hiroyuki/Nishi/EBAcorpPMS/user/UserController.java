package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUp(SignupForm signupForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(@Validated SignupForm signupForm,
                             BindingResult bindingResult,
                             Model model) {
        if (!bindingResult.hasErrors()) {
            JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
            if (users.userExists(signupForm.getUsername())) {
                System.out.println("Already exist");
                model.addAttribute("signupError", "ユーザー名 " + signupForm.getUsername() + "は既に登録されています");
            } else {
                UserDetails newUser = User.builder()
                        .username(signupForm.getUsername())
                        .password(passwordEncoder.encode(signupForm.getPassword1()))
                        .roles("USER")
                        .build();
                try {
                    users.createUser(newUser);
                    model.addAttribute("message", "ユーザー登録に成功しました。");
                    return "login";
                } catch (DataAccessException e) {
                    model.addAttribute("signupError", "ユーザー登録に失敗しました。");
                }
            }
        }
        return "signup";
    }
}
