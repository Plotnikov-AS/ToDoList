package main.controller;

import main.model.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;



    @GetMapping("/tasks/")
    public List<Task> listAllCases(){
        Iterable<Task> taskIterable = taskRepo.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable){
            tasks.add(task);
        }
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getCase(@PathVariable int id){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }


    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteCase(@PathVariable int id){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isPresent()){
            taskRepo.delete(optionalTask.get());
            return ResponseEntity.status(HttpStatus.OK).body("Delete success");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



    @PostMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable int id, String newDesc){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setDescription(newDesc);
            taskRepo.save(task);
            return new ResponseEntity(task, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
