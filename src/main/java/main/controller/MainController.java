package main.controller;

import main.model.Task;
import main.model.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private TaskRepo taskRepo;

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        return "index";
    }

    @RequestMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "main";
    }

    @PostMapping("/main")
    public String addTask(@RequestParam String shortDesc,
                          @RequestParam String description,
                          @RequestParam(name = "status", required = false, defaultValue = "NEW") Task.TaskStatus status,
                          Map<String, Object> model){
        Task task = new Task(shortDesc, description, status);
        taskRepo.save(task);
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "main";
    }

    @PostMapping("filterById")
    public String filterById(@RequestParam Integer filter, Map<String, Object> model){
        if (filter == null){
            Iterable<Task> tasks = taskRepo.findAll();
            model.put("tasks", tasks);
        }
        else {
            Optional<Task> optionalTask = taskRepo.findById(filter);
            if (optionalTask.isPresent()) {
                model.put("tasks", optionalTask.get());
            }
        }
        return "main";
    }
    @PostMapping("filterByShortDesc")
    public String filterByShortDesc(@RequestParam String filter, Map<String, Object> model){
        Iterable<Task> tasks;
        if (filter == null || filter.isEmpty()){
            tasks = taskRepo.findAll();
        }
        else {
            tasks = taskRepo.findByShortDesc(filter);
        }
        model.put("tasks", tasks);
        return "main";
    }
}
