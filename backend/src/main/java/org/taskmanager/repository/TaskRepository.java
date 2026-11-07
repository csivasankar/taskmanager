/**
 * Repository interface for performing database operations
 * on Task entities.
 *
 * Uses Spring Data JPA to provide CRUD functionality.
 *
 * @author Sivasankar
 * @version 1.0
 * @since 2026-11-07
 */

package org.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.entity.Task;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

}