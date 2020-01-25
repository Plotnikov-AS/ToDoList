package main.controller;

import main.model.Task;
import main.model.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    public String searchForm(Model model){
        Iterable<Task> tasks = new ArrayList<>();
        model.addAttribute("tasks", tasks);
        return "search";
    }

    @PostMapping
    public String searchById(@RequestParam Integer id, Model model){
        if (id == null){
            Iterable<Task> tasks = taskRepo.findAll();
            model.addAttribute("tasks", tasks);
        }
        else {
            Optional<Task> optionalTask = taskRepo.findById(id);
            if (optionalTask.isPresent()) {
                List<Task> tasks = new ArrayList<>();
                tasks.add(optionalTask.get());
                model.addAttribute("tasks", tasks);
            }
        }
        return "search";
    }
    @PostMapping(path = "/searchByTag")
    public String searchByTag(@RequestParam String tag, Model model){
        Iterable<Task> tasks;
        if (tag == null || tag.isEmpty()){
            tasks = taskRepo.findAll();
        }
        else {
            tasks = taskRepo.findByTag(tag);
        }
        model.addAttribute("tasks", tasks);
        return "search";
    }
}
