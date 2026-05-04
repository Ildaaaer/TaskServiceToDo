package ildaaaer.dto;


import ildaaaer.entity.Priority;
import ildaaaer.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDto {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Priority taskPriority;
    private Status taskStatus;
    private Long assigneeId;
}
