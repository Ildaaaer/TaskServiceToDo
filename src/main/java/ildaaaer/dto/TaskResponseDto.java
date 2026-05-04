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
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Status taskStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private Priority taskPriority;
    private Long assigneeId;
}
