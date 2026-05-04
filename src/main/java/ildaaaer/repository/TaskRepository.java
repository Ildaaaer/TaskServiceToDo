package ildaaaer.repository;

import ildaaaer.entity.Priority;
import ildaaaer.entity.Status;
import ildaaaer.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    /*List<Task> findByUserId(Long userId);*/
    Stream<Task> streamAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword, String descriptionKeyword);


    List<Task> findAllByStatus(Status status);

    List<Task> findAllByTaskPriority(Priority taskPriority);

    List<Task> findAllByAssigneeId(Long assigneeId);

    List<Task> findAllByDueDateBefore(LocalDateTime dueDate);

    List<Task> findAllByStatusAndAssigneeId(Status status, Long assigneeId);

}