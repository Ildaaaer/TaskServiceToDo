package ildaaaer.factories;

import ildaaaer.dto.TaskResponseDto;
import ildaaaer.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseDtoFactory {
    public TaskResponseDto makeTaskResponseDto(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .taskPriority(task.getTaskPriority())
                .assigneeId(task.getAssigneeId())
                .build();
    }
}
