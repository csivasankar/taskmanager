
/**
 * Service interface defining business operations
 * related to task management.
 *
 * @author Sivasankar
 * @version 1.0
 * @since 2026-11-19
 */

package org.taskmanager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taskmanager.dto.TaskRequest;
import org.taskmanager.dto.TaskResponse;
import org.taskmanager.entity.Task;

public interface TaskService {

    /**
     * Creates a new task.
     *
     * @param request task creation details
     * @return created task response
     */
    TaskResponse createTask(TaskRequest request);

    /**
     * Retrieves all tasks with pagination.
     *
     * @param pageable pagination information
     * @return paginated task list
     */
    Page<TaskResponse> getAllTasks(Pageable pageable);
}
