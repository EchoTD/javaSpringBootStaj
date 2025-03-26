package com.example.task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@PostMapping
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id) {
		return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
		task.setTitle(taskDetails.getTitle());
		task.setDescriptions(taskDetails.getDescription());
		task.setCompleted(taskDetails.isCompleted());

		return taskRepository.save(task);
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id) {
		taskRepository.deleteById(id);
	}
}
