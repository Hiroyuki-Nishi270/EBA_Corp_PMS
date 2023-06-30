package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task;

import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.CRUDGanttTaskDataRepository;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt.GanttTaskData;
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

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    CRUDTaskFormRepository TaskFormRepository;

    @Autowired
    CRUDGanttTaskDataRepository FrappeGanttTaskDataRepository;

    @Autowired
    CRUDAttachFileRepository attachFileRepository;

    @GetMapping("/new")
    public String getNewTask(Model model){
        List<GanttTaskData> TasksListShort = (List<GanttTaskData>) FrappeGanttTaskDataRepository.findAll();

        TaskFormEntity TaskFormEntity = new TaskFormEntity();
        model.addAttribute("taskListShort", TasksListShort);
        model.addAttribute("taskFormEntity", TaskFormEntity);
        model.addAttribute("JsTaskFormEntity", null);
        model.addAttribute("JsTaskListShort", TasksListShort);


        return "ticketdetail";
    }

    @PostMapping("/new")
    public String postNewTask(@Validated TaskFormEntity TaskFormEntity,
                              BindingResult bindingResult,
                              Model model){
        List<GanttTaskData> TasksListShort = (List<GanttTaskData>) FrappeGanttTaskDataRepository.findAll();
        if (!bindingResult.hasErrors()) {
            try{
                TaskFormRepository.save(TaskFormEntity);
                model.addAttribute("message", "タスク登録に成功しました");
            }catch(Exception e){
                model.addAttribute("message", "タスク登録に失敗しました");

            }
        }
        model.addAttribute("taskListShort", TasksListShort);
        model.addAttribute("taskFormEntity", TaskFormEntity);
        model.addAttribute("JsTaskFormEntity", TaskFormEntity);
        model.addAttribute("JsTaskListShort", TasksListShort);
        return "ticketdetail";
    }

    @GetMapping("/{id}")
    public String getTaskDetail(@PathVariable Integer id,
                                Model model){

        TaskFormEntity taskFormEntity = TaskFormRepository.findById(id).get();
        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);
        List<GanttTaskData> TaskListShort = (List<GanttTaskData>) FrappeGanttTaskDataRepository.findAll();

        //List<String> Dependencies = Arrays.asList(taskFormEntity.getDependencies().split(","));
        System.out.println(TaskListShort);
        //System.out.println(Dependencies);

        model.addAttribute("taskListShort", TaskListShort);
        model.addAttribute("taskFormEntity", taskFormEntity);
        model.addAttribute("JsTaskFormEntity", taskFormEntity);
        model.addAttribute("JsTaskListShort", TaskListShort);
        model.addAttribute("attachFileEntity", attachFileEntities);

        return "ticketdetail";
    }
    @PostMapping("/{id}")
    public String postTaskDetail(@PathVariable Integer id,
                                 @Validated TaskFormEntity taskFormEntity,
                                 BindingResult bindingResult,
                                 Model model){
        List<GanttTaskData> TaskListShort = (List<GanttTaskData>) FrappeGanttTaskDataRepository.findAll();

        if (!bindingResult.hasErrors()) {
            try {
                TaskFormRepository.save(taskFormEntity);
                model.addAttribute("message", "タスク更新に成功しました");
            } catch (Exception e) {
                model.addAttribute("message", "タスク更新に失敗しました");
            }
        }

        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);

        model.addAttribute("taskListShort", TaskListShort);
        model.addAttribute("taskFormEntity", taskFormEntity);
        model.addAttribute("JsTaskFormEntity", taskFormEntity);
        model.addAttribute("JsTaskListShort", TaskListShort);
        model.addAttribute("attachFileEntity", attachFileEntities);
        return "ticketdetail";

    }
}
