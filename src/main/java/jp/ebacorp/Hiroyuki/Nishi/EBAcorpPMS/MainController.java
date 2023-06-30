package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    String postIndex(@RequestParam("updated_task_list_by_gantt") String receive
    ){

        try {
            GanttTaskData[] convertedGanttTaskData = new ObjectMapper().readValue(receive, GanttTaskData[].class);

            for (GanttTaskData x: convertedGanttTaskData
                 ) {
                ganttTaskDataRepository.save(x);
            }
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:";
    }

}
