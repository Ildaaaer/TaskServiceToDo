package ildaaaer.factories;

import ildaaaer.dto.TaskSearchDto;
import ildaaaer.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskSearchDtoFactory {
    public TaskSearchDto makeTaskSearchDto(Task task) {
        return TaskSearchDto.builder()
                .title(task.getTitle())
                .taskPriority(task.getTaskPriority())
                .taskStatus(task.getStatus())
                .dueDate(task.getDueDate())
                .build();
    }
}
