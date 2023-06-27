package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task;

import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.CRUDFrappeGanttTaskDataRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.FrappeGanttTaskData;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage.AttachFileEntity;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage.CRUDAttachFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    CRUDTaskFormRepository TaskFormRepository;

    @Autowired
    CRUDFrappeGanttTaskDataRepository FrappeGanttTaskDataRepository;

    @Autowired
    CRUDAttachFileRepository attachFileRepository;

    @GetMapping("/new")
    public String getNewTask(TaskFormEntity taskFormEntity){
        return "ticketdetail";
    }

    @PostMapping("/new")
    public String postNewTask(@Validated TaskFormEntity taskFormEntity,
                              BindingResult bindingResult,
                              Model model){
        if (!bindingResult.hasErrors()) {
            try{

                TaskFormRepository.save(taskFormEntity);
                model.addAttribute("message", "タスク登録に成功しました");
            }catch(Exception e){
                model.addAttribute("message", "タスク登録に失敗しました");
            }
        }
        return "ticketdetail";
    }

    @GetMapping("/{id}")
    public String getTaskDetail(@PathVariable Integer id,
                                Model model){

        TaskFormEntity taskFormEntity = TaskFormRepository.findById(id).get();
        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);
        List<FrappeGanttTaskData> TaskListShort = (List<FrappeGanttTaskData>) FrappeGanttTaskDataRepository.findAll();

        //List<String> Dependencies = Arrays.asList(taskFormEntity.getDependencies().split(","));
        System.out.println(TaskListShort);
        //System.out.println(Dependencies);

        model.addAttribute("taskFormEntity", taskFormEntity);
        model.addAttribute("attachFileEntity", attachFileEntities);
        model.addAttribute("taskListShort", TaskListShort);
        //model.addAttribute("dependencies", Dependencies);

        return "ticketdetail";
    }
    @PostMapping("/{id}")
    public String postTaskDetail(@PathVariable Integer id,
                                 @Validated TaskFormEntity taskFormEntity,
                                 BindingResult bindingResult,
                                 Model model){
        if (!bindingResult.hasErrors()) {
            try {
                TaskFormRepository.save(taskFormEntity);
                model.addAttribute("message", "タスク更新に成功しました");
            } catch (Exception e) {
                model.addAttribute("message", "タスク更新に失敗しました");
            }
        }

        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);

        model.addAttribute("taskForm", taskFormEntity);
        model.addAttribute("attachFileEntity", attachFileEntities);
        return "ticketdetail";

    }
}
