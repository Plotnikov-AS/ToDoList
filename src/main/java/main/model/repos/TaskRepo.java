package main.model.repos;

import main.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
    List<Task> findByTag(String tag);
}
