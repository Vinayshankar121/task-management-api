package com.wellness360.taskmanagement.dto;

import com.wellness360.taskmanagement.model.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskRequest {
	@NotBlank(message = "Title is required")
	private String title;

	private String description;

	@NotNull(message = "Due date is required")
	@FutureOrPresent(message = "Due date must be today or in the future")
	private LocalDate dueDate;

	private TaskStatus status;
}