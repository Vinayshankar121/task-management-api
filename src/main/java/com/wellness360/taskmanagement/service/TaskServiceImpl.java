package com.wellness360.taskmanagement.service;

import com.wellness360.taskmanagement.dto.TaskRequest;
import com.wellness360.taskmanagement.dto.TaskResponse;
import com.wellness360.taskmanagement.exception.ResourceNotFoundException;
import com.wellness360.taskmanagement.model.Task;
import com.wellness360.taskmanagement.model.TaskStatus;
import com.wellness360.taskmanagement.repository.TaskRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<TaskResponse> getAllTasks() {
		return taskRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Override
	public TaskResponse getTaskById(Long id) {
		return toResponse(taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id)));
	}

	@Override
	public TaskResponse createTask(TaskRequest request) {
		LocalDateTime now = LocalDateTime.now();
		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setDueDate(request.getDueDate());
		task.setStatus(normalizeStatus(request.getStatus()));
		task.setCreatedAt(now);
		task.setUpdatedAt(now);
		return toResponse(taskRepository.save(task));
	}

	@Override
	public TaskResponse updateTask(Long id, TaskRequest request) {
		Task existingTask = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

		existingTask.setTitle(request.getTitle());
		existingTask.setDescription(request.getDescription());
		existingTask.setDueDate(request.getDueDate());
		existingTask.setStatus(normalizeStatus(request.getStatus()));
		existingTask.setUpdatedAt(LocalDateTime.now());
		return toResponse(taskRepository.save(existingTask));
	}

	@Override
	public TaskResponse markTaskAsComplete(Long id) {
		Task existingTask = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

		existingTask.setStatus(TaskStatus.COMPLETED);
		existingTask.setUpdatedAt(LocalDateTime.now());
		return toResponse(taskRepository.save(existingTask));
	}

	@Override
	public void deleteTask(Long id) {
		if (!taskRepository.existsById(id)) {
			throw new ResourceNotFoundException("Task not found with id: " + id);
		}
		taskRepository.deleteById(id);
	}

	private TaskStatus normalizeStatus(TaskStatus status) {
		return status == null ? TaskStatus.PENDING : status;
	}

	private TaskResponse toResponse(Task task) {
		return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
	}
}