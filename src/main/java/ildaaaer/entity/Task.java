package ildaaaer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    private Long assigneeId;

    @JsonProperty("created_At")
    private LocalDateTime createdAt;

    @JsonProperty("updated_At")
    private LocalDateTime updatedAt;

    @JsonProperty("due_Date")
    private LocalDateTime dueDate;
}
