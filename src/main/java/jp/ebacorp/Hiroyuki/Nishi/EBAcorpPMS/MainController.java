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
    CRUDGanttTaskDataRepository GanttTaskDataRepository;

    @GetMapping
    String getIndex(Model model){
        List<GanttTaskData> Tasks = (List<GanttTaskData>) GanttTaskDataRepository.findAll();
        List<GanttTaskData> TasksForGantt = (List<GanttTaskData>) GanttTaskDataRepository.findAllByOrderByStart();

        model.addAttribute("taskList",Tasks);
        model.addAttribute("ganttTaskList",Tasks);
        return "index";
    }

}
