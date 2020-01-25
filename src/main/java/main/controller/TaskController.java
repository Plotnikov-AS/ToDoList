package main.controller;

import main.model.Statuses.TaskStatus;
import main.model.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("{task}")
    public String editingTaskForm(@PathVariable Task task, Model model){
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/new")
    public String addingTaskForm(Model model){
        String shortDesc = "";
        String description = "";
        TaskStatus status = TaskStatus.NEW;
        Task task = new Task(shortDesc, description, status);
        taskRepo.save(task);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping
    public String updateTask(@RequestParam Integer id,
                             @RequestParam String tag,
                             @RequestParam String description){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setTag(tag);
            task.setDescription(description);
            taskRepo.save(task);
        }
        else {
            System.out.println("Err: task doesn't exist");
        }
        return "redirect:/main";
    }

//    @DeleteMapping("{task}")
//    public String deleteTask(@PathVariable Task task) {
//        taskRepo.delete(task);
//        return "redirect:/main";
//    }
}
