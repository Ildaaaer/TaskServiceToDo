package ildaaaer.factories;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskRequestDtoFactory {
    public TaskRequestDto makeTaskRequestDTO(Task task) {
        return TaskRequestDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .assigneeId(task.getAssigneeId())
                .priority(task.getTaskPriority())
                .status(task.getStatus())
                .build();
    }
}
