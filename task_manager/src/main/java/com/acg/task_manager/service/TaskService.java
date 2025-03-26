package com.acg.task_manager.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.acg.task_manager.model.Task;
import com.acg.task_manager.model.TaskStatus;
import com.acg.task_manager.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Iterable<Task> get() {
		return taskRepository.findAll();
	}

	public Task get(Integer id) {
		return taskRepository.findById(id).orElse(null);
	}

	public void remove(Integer id) {
		taskRepository.deleteById(id);
	}

	public Task save(String title, String description, LocalDateTime dueDate) {
		Task task = new Task();

		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(TaskStatus.TODO);
		task.setDueDate(dueDate);
		task.setCreatedAt(LocalDateTime.now());
		task.setUpdatedAt(LocalDateTime.now());

		return taskRepository.save(task);
	}
}
