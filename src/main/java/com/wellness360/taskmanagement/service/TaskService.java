package com.wellness360.taskmanagement.service;

import com.wellness360.taskmanagement.dto.TaskRequest;
import com.wellness360.taskmanagement.dto.TaskResponse;
import java.util.List;

public interface TaskService {
	List<TaskResponse> getAllTasks();

	TaskResponse getTaskById(Long id);

	TaskResponse createTask(TaskRequest request);

	TaskResponse updateTask(Long id, TaskRequest request);

	TaskResponse markTaskAsComplete(Long id);

	void deleteTask(Long id);
}