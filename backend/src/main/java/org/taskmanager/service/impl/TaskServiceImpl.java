
/**
 * Service implementation responsible for executing
 * business logic related to task management.
 *
 * Handles task creation and retrieval with pagination.
 *
 * @author Sivasankar
 * @version 1.0
 * @since 2026-11-19
 */

package org.taskmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taskmanager.dto.TaskRequest;
import org.taskmanager.dto.TaskResponse;
import org.taskmanager.entity.Task;
import org.taskmanager.mapper.TaskMapper;
import org.taskmanager.repository.TaskRepository;
import org.taskmanager.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log =
            LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse createTask(TaskRequest request) {
        log.info("Creating task with title: {}", request.getTitle());
        try {
            Task task = taskMapper.toEntity(request);
            Task savedTask = taskRepository.save(task);
            log.info("Task created successfully. Task ID: {}",
                    savedTask.getId());
            return taskMapper.toResponse(savedTask);
        } catch (RuntimeException ex) {
            log.error("Failed to create task. Title: {}",
                    request.getTitle(), ex);
            throw ex;
        }
    }

    @Override
    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        log.debug("Fetching tasks. Page: {}, Size: {}",
                pageable.getPageNumber(),
                pageable.getPageSize());
        Page<Task> tasks = taskRepository.findAll(pageable);
        log.info("Successfully retrieved {} tasks",
                tasks.getNumberOfElements());
        return tasks.map(taskMapper::toResponse);
    }
}
