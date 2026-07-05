package com.wellness360.taskmanagement.repository;

import com.wellness360.taskmanagement.model.Task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
	private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0);

	public List<Task> findAll() {
		List<Task> taskList = new ArrayList<>(tasks.values());
		taskList.sort(Comparator.comparing(Task::getId));
		return taskList;
	}

	public Optional<Task> findById(Long id) {
		return Optional.ofNullable(tasks.get(id));
	}

	public Task save(Task task) {
		if (task.getId() == null) {
			task.setId(sequence.incrementAndGet());
		}
		tasks.put(task.getId(), task);
		return task;
	}

	public void deleteById(Long id) {
		tasks.remove(id);
	}

	public boolean existsById(Long id) {
		return tasks.containsKey(id);
	}
}