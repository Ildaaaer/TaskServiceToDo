package ildaaaer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "task_seq",
            allocationSize = 1
    )
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @Enumerated(EnumType.STRING)
    private Priority taskPriority;

    private Long assigneeId;

    @JsonProperty("created_At")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonProperty("updated_At")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonProperty("due_Date")
    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",  nullable = false)
    private Project project;

}
