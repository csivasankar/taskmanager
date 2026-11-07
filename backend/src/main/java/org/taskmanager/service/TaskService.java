/**
 * Service layer responsible for implementing business logic
 * related to task management.
 *
 * Handles task creation and retrieval.
 *
 * @author Sivasankar
 * @version 1.0
 * @since 2026-11-07
 */

package org.taskmanager.service;

import org.springframework.stereotype.Service;
import org.taskmanager.dto.TaskRequest;
import org.taskmanager.entity.Task;
import org.taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}