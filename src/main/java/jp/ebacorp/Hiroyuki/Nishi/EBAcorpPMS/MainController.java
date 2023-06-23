package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;


import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.CRUDTaskFormRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.TaskForm;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage.AttachFileEntity;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage.CRUDAttachFileRepository;
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
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CRUDTaskFormRepository TaskFormRepository;

    @Autowired
    CRUDAttachFileRepository attachFileRepository;

    @Autowired
    DataSource dataSource;
    @GetMapping
    String getIndex(Model model){
        List<TaskForm> TaskList = (List<TaskForm>) TaskFormRepository.findAll();

        model.addAttribute("taskTest",TaskList);
        model.addAttribute("JSFS", "JavascriptFromSpring");
        return "index";
    }

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


    @GetMapping("/newtask")
    public String getNewTask(TaskForm taskForm,
                             AttachFileEntity attachFileEntity){
        return "ticketdetail";
    }

    @PostMapping("/newtask")
    public String postNewTask(@Validated TaskForm taskForm,
                           BindingResult bindingResult,
                           Model model){
        if (!bindingResult.hasErrors()) {
            try{

                TaskFormRepository.save(taskForm);
                model.addAttribute("message", "タスク登録に成功しました");
                }catch(Exception e){
                model.addAttribute("message", "タスク登録に失敗しました");
            }
        }
        return "ticketdetail";
    }

    @GetMapping("/ticket/{id}")
    public String getTaskDetail(@PathVariable Integer id,
                                 TaskForm taskForm,
                                 Model model,
                                AttachFileEntity attachFileEntity){

        Optional<TaskForm> taskFormFromDB = TaskFormRepository.findById(id);
        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);

        taskForm = taskFormFromDB.get();

        model.addAttribute("taskForm", taskForm);
        model.addAttribute("attachFileEntity", attachFileEntities);

        return "ticketdetail";
    }
    @PostMapping("/ticket/{id}")
    public String postTaskDetail(@PathVariable Integer id,
                                 @Validated TaskForm taskForm,
                                 BindingResult bindingResult,
                                 Model model){
        if (!bindingResult.hasErrors()) {
            try{
                TaskFormRepository.save(taskForm);
                model.addAttribute("message", "タスク更新に成功しました");
            }catch(Exception e){
                model.addAttribute("message", "タスク更新に失敗しました");
            }
        }
        return "ticketdetail";


    }

}
