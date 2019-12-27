package main;

import main.model.Task;
import main.model.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    private TaskRepo taskRepo;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Task> taskIterable = taskRepo.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable){
            tasks.add(task);
        }
        model.addAttribute("tasksCount", tasks.size());
        model.addAttribute("tasks", tasks);

        return "index";
    }
}
