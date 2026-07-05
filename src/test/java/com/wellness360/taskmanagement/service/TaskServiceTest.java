package com.wellness360.taskmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.wellness360.taskmanagement.dto.TaskRequest;
import com.wellness360.taskmanagement.dto.TaskResponse;
import com.wellness360.taskmanagement.model.TaskStatus;
import com.wellness360.taskmanagement.repository.TaskRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
	private final TaskService taskService = new TaskServiceImpl(new TaskRepository());

	@Test
	void createTaskStoresTask() {
		TaskRequest request = new TaskRequest();
		request.setTitle("Write API");
		request.setDescription("Build the task management endpoints");
		request.setDueDate(LocalDate.now());
		request.setStatus(TaskStatus.IN_PROGRESS);

		TaskResponse response = taskService.createTask(request);

		assertNotNull(response.getId());
		assertEquals("Write API", response.getTitle());
		assertEquals(TaskStatus.IN_PROGRESS, response.getStatus());
		assertNotNull(response.getDueDate());
	}

	@Test
	void markTaskAsCompleteSetsCompletedStatus() {
		TaskRequest request = new TaskRequest();
		request.setTitle("Finish docs");
		request.setDescription("Document the API");
		request.setDueDate(LocalDate.now());
		request.setStatus(TaskStatus.PENDING);

		TaskResponse created = taskService.createTask(request);
		TaskResponse completed = taskService.markTaskAsComplete(created.getId());

		assertEquals(TaskStatus.COMPLETED, completed.getStatus());
	}

	@Test
	void getTaskByIdThrowsWhenMissing() {
		assertThrows(RuntimeException.class, () -> taskService.getTaskById(999L));
	}
}