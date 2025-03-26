package com.acg.task_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acg.task_manager.dto.request.TaskRequest;
import com.acg.task_manager.model.Task;
import com.acg.task_manager.service.TaskService;

@RestController
public class TaskController {
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/tasks")
	public Iterable<Task> get() {
		return taskService.get();
	}

	@GetMapping("/tasks/{id}")
	public Task getTaskById(@PathVariable Integer id) {
		Task task = taskService.get(id);
		if (task == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return task;
	}

	@DeleteMapping("/tasks/{id}")
	public void deleteTaskById(@PathVariable Integer id) {
		taskService.remove(id);
	}

	@PostMapping("/tasks")
	public Task createTask(@RequestBody TaskRequest request) {
		return taskService.save(
				request.getTitle(),
				request.getDescription(),
				request.getDueDate());
	}
}
