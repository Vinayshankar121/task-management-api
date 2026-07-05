package com.wellness360.taskmanagement.dto;

import com.wellness360.taskmanagement.model.TaskStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
	private Long id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private TaskStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}