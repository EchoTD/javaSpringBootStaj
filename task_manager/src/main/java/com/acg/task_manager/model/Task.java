package com.acg.task_manager.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table("TASKS")
public class Task {

	@Id
	private Long id;
	@NotBlank(message = "Title must not be empty.")
	@Size(min = 2, max = 100, message = "Title must be in range of 2 to 100 characters.")
	private String title;

	@Size(max = 500, message = "Description too long (max 500 characters).")
	private String description;

	private TaskStatus status = TaskStatus.TODO;

	private LocalDateTime dueDate;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	// private Long createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	// public Long getCreatedBy() {
	// return createdBy;
	// }
	//
	// public void setCreatedBy(Long createdBy) {
	// this.createdBy = createdBy;
	// }
}
