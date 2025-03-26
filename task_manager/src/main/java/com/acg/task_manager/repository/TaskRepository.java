package com.acg.task_manager.repository;

import org.springframework.data.repository.CrudRepository;
import com.acg.task_manager.model.*;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
