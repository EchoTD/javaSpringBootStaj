package com.acg.task_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acg.task_manager.dto.request.TaskRequest;
import com.acg.task_manager.model.Task;
import com.acg.task_manager.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public Iterable<Task> get() {
		return taskService.get();
	}

	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id) {
		Task task = taskService.get(id);
		if (task == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return task;
	}

	@DeleteMapping("/{id}")
	public void deleteTaskById(@PathVariable Long id) {
		taskService.remove(id);
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
		return taskService.update(id, task);
	}

	@PostMapping
	public Task createTask(@RequestBody TaskRequest request) {
		return taskService.save(
				request.getTitle(),
				request.getDescription(),
				request.getStatus(),
				request.getDueDate());
	}
}
