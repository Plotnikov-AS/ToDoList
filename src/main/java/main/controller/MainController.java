package main.controller;

import main.model.Task;
import main.model.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private TaskRepo taskRepo;

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        return "index";
    }

    @RequestMapping("/main")
    public String listAllTasks(Model model){
        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("tasks", tasks);
        return "main";
    }

}
