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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taskmanager.dto.TaskRequest;
import org.taskmanager.dto.TaskResponse;
import org.taskmanager.entity.Task;
import org.taskmanager.mapper.TaskMapper;
import org.taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponse createTask(TaskRequest request) {
        Task task = taskMapper.toEntity(request);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}