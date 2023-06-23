package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;


import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.CRUDTaskFormRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    CRUDTaskFormRepository TaskFormRepository;

    @GetMapping
    String getIndex(Model model){
        List<TaskForm> TaskList = (List<TaskForm>) TaskFormRepository.findAll();

        model.addAttribute("taskTest",TaskList);
        model.addAttribute("JSFS", "JavascriptFromSpring");
        return "index";
    }

}
