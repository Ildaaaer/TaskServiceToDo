package ildaaaer.dto;

import ildaaaer.entity.TaskPriority;
import ildaaaer.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskSearchDto {
    private String title;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;
    private LocalDateTime dueDate;
}
