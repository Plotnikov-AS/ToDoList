package main.controller;

import main.model.Statuses.TaskStatus;
import main.model.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

//
//    @GetMapping("/tasks/")
//    public List<Task> listAllCases(){
//        Iterable<Task> taskIterable = taskRepo.findAll();
//        ArrayList<Task> tasks = new ArrayList<>();
//        for (Task task : taskIterable){
//            tasks.add(task);
//        }
//        return tasks;
//    }
//
//    @GetMapping("/tasks/{id}")
//    public ResponseEntity getCase(@PathVariable int id){
//        Optional<Task> optionalTask = taskRepo.findById(id);
//        if (!optionalTask.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/tasks/{id}")
//    public ResponseEntity deleteCase(@PathVariable int id){
//        Optional<Task> optionalTask = taskRepo.findById(id);
//        if (optionalTask.isPresent()){
//            taskRepo.delete(optionalTask.get());
//            return ResponseEntity.status(HttpStatus.OK).body("Delete success");
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
}
