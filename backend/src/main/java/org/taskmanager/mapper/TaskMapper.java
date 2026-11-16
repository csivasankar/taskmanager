package org.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.taskmanager.dto.TaskRequest;
import org.taskmanager.dto.TaskResponse;
import org.taskmanager.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // Request DTO to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Task toEntity(TaskRequest request);

    // Entity to Response DTO
    TaskResponse toResponse(Task task);
}