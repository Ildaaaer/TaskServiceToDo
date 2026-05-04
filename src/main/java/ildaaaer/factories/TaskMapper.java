package ildaaaer.factories;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.dto.TaskResponseDto;
import ildaaaer.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequestDto dto){
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .taskPriority(dto.getTaskPriority())
                .taskStatus(dto.getTaskStatus())
                .assigneeId(dto.getAssigneeId())
                .build();
    }

    public TaskResponseDto toResponseDto(Task task){
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .taskStatus(task.getTaskStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .assigneeId(task.getAssigneeId())
                .taskPriority(task.getTaskPriority())
                .build();

    }
}
