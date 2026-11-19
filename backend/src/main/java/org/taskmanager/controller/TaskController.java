
/**
 * REST Controller responsible for managing task operations.
 *
 * Provides APIs to create and retrieve tasks with pagination
 * and sorting support.
 *
 * @author Sivasankar
 * @version 1.1
 * @since 2026-11-07
 */

package org.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.taskmanager.dto.TaskRequest;
import org.taskmanager.dto.TaskResponse;
import org.taskmanager.entity.Task;
import org.taskmanager.exception.ApiError;
import org.taskmanager.service.TaskService;

import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(
        name = "Task Management",
        description = "APIs for creating and retrieving tasks"
)
public class TaskController {

    private final TaskService taskService;

    private static final Set<String> ALLOWED_SORT_FIELDS =
            Set.of("id", "title", "description", "createdAt");

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Creates a new task.
     */
    @Operation(
            summary = "Create a new task",
            description = "Creates a task using a title and optional description."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Task created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "title": "Create Kanban story board",
                                              "description": "Create a task for Kanban Board",
                                              "createdAt": "2026-11-19T10:30:00"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or validation failure",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Task details required for creation",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = TaskRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "title": "Create Kanban story board",
                                              "description": "Create a task for Kanban Board"
                                            }
                                            """
                            )
                    )
            )
            TaskRequest request) {
        TaskResponse createdTask = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTask);
    }

    /**
     * Retrieves tasks with pagination and sorting.
     */
    @Operation(
            summary = "Retrieve all tasks",
            description = """
                    Retrieves tasks with pagination and sorting support.
                    Page numbering starts from zero.
                    Maximum page size is 100.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tasks retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = Page.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination or sorting parameters",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            @Parameter(
                    description = "Page number (zero-based)",
                    example = "0",
                    schema = @Schema(minimum = "0", defaultValue = "0")
            )
            @RequestParam(defaultValue = "0")
            int page,
            @Parameter(
                    description = "Number of tasks per page (maximum 100)",
                    example = "10",
                    schema = @Schema(minimum = "1", maximum = "100",
                            defaultValue = "10")
            )
            @RequestParam(defaultValue = "10")
            int size,
            @Parameter(
                    description = "Field used for sorting. Allowed: id, title, description, createdAt",
                    example = "createdAt"
            )
            @RequestParam(defaultValue = "id")
            String sortBy,
            @Parameter(
                    description = "Sorting direction: asc or desc",
                    example = "desc",
                    schema = @Schema(defaultValue = "desc",
                            allowableValues = {"asc", "desc"})
            )
            @RequestParam(defaultValue = "desc")
            String direction) {
        if (page < 0 || size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page must be non-negative and size must be between 1 and 100"
            );
        }
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException(
                    "Invalid sorting field: " + sortBy
            );
        }
        Sort.Direction sortDirection;
        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Direction must be either 'asc' or 'desc'");
        }
        Pageable pageable = PageRequest.of(
                page, size, Sort.by(sortDirection, sortBy)
        );
        Page<TaskResponse> tasks = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(tasks);
    }
}
