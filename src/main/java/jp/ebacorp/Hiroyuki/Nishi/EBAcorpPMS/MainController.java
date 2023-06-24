package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;


import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.CRUDTaskFormRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.CRUDGanttTaskDataRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.GanttTaskData;
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

    @Autowired
    CRUDGanttTaskDataRepository GanttTaskDataRepository;

    @GetMapping
    String getIndex(Model model){
        //List<TaskForm> TaskList = (List<TaskForm>) TaskFormRepository.findAll();
        List<GanttTaskData> TaskList = (List<GanttTaskData>) GanttTaskDataRepository.findAll();

        model.addAttribute("taskList",TaskList);
        model.addAttribute("ganttTaskList",TaskList);
        return "index";
    }

}
