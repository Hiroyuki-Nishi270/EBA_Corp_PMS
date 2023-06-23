package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task;

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
import java.util.Optional;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    CRUDTaskFormRepository TaskFormRepository;

    @Autowired
    CRUDAttachFileRepository attachFileRepository;

    @GetMapping("/new")
    public String getNewTask(TaskForm taskForm){
        return "ticketdetail";
    }

    @PostMapping("/new")
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

    @GetMapping("/{id}")
    public String getTaskDetail(@PathVariable Integer id,
                                Model model
    ){

        Optional<TaskForm> taskFormFromDB = TaskFormRepository.findById(id);
        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);

        TaskForm taskForm = taskFormFromDB.get();

        model.addAttribute("taskForm", taskForm);
        model.addAttribute("attachFileEntity", attachFileEntities);

        return "ticketdetail";
    }
    @PostMapping("/{id}")
    public String postTaskDetail(@PathVariable Integer id,
                                 @Validated TaskForm taskForm,
                                 BindingResult bindingResult,
                                 Model model){
        if (!bindingResult.hasErrors()) {
            try {
                TaskFormRepository.save(taskForm);
                model.addAttribute("message", "タスク更新に成功しました");
            } catch (Exception e) {
                model.addAttribute("message", "タスク更新に失敗しました");
            }
        }

        List<AttachFileEntity> attachFileEntities = attachFileRepository.findByTicketidEquals(id);

        model.addAttribute("taskForm", taskForm);
        model.addAttribute("attachFileEntity", attachFileEntities);
        return "ticketdetail";

    }
}
