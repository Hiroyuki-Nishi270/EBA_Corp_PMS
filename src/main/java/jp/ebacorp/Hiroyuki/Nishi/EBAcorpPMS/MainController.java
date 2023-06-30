package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;


import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.CRUDGanttTaskDataRepository;

import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.GanttTaskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    CRUDGanttTaskDataRepository ganttTaskDataRepository;


    @GetMapping
    String getIndex(Model model){
        List<GanttTaskData> TasksListShort = (List<GanttTaskData>) ganttTaskDataRepository.findAll();

        model.addAttribute("taskList",TasksListShort);
        model.addAttribute("ganttTaskList",TasksListShort);
        model.addAttribute("JsTaskListShort", TasksListShort);
        return "index";
    }

    @PostMapping
    String postIndex(Model model,
                     @RequestParam("task[0].id") String test
            /*taskList<GanttTaskData> Tasks*/){

        //System.out.println(Tasks);

        //model.addAttribute("taskList",Tasks);
        //model.addAttribute("ganttTaskList",Tasks);
        return "index";
    }

}
