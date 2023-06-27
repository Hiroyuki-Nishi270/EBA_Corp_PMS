package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;


import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.CRUDFrappeGanttTaskDataRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.FrappeGanttTaskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    CRUDFrappeGanttTaskDataRepository FrappeGanttTaskDataRepository;

    @GetMapping
    String getIndex(Model model){
        List<FrappeGanttTaskData> Tasks = (List<FrappeGanttTaskData>) FrappeGanttTaskDataRepository.findAll();
        List<FrappeGanttTaskData> TasksForGantt = FrappeGanttTaskDataRepository.findAllByOrderByStartAscEndAsc();

        model.addAttribute("taskList",TasksForGantt);
        model.addAttribute("ganttTaskList",TasksForGantt);
        return "index";
    }

}
