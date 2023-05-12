package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping({"/","/index","index"})
    String indexController(Model model){
        //List<TaskItem> taskItems = dao.findAll();
        //model.addAttribute("taskList", taskItems);

        model.addAttribute("title","インデックス");

        System.out.println("index is called");
        return "index";
    }
}
