package ildaaaer.service;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.dto.TaskResponseDto;
import ildaaaer.entity.Priority;
import ildaaaer.entity.Status;
import ildaaaer.entity.Task;
import ildaaaer.exceptions.BadRequestException;
import ildaaaer.exceptions.NotFoundException;
import ildaaaer.factories.TaskMapper;
import ildaaaer.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }


    public List<TaskResponseDto> fetchTasks(String keywords){
        if (keywords == null || keywords.isBlank()){
           throw new BadRequestException("Keywords name must not be empty or null");
        }

        String[] words = keywords.trim().split("\\s+");
        Set<Task> matchedTasks = new HashSet<>();
        for (String word : words) {
            taskRepository
                    .streamAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(word, word)
                    .forEach(matchedTasks::add);
        }

        if(matchedTasks.isEmpty()){
            throw new NotFoundException("No tasks found");
        }
        return matchedTasks
                .stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }


    public TaskResponseDto createTask(TaskRequestDto taskRequestDto){
        taskRepository.findByTitle(taskRequestDto.getTitle())
                .ifPresent(t -> {
                    throw new BadRequestException("Task already exists");
                });
        Task task = taskMapper.toEntity(taskRequestDto);
        Task savedTask = taskRepository.saveAndFlush(task);
        return taskMapper.toResponseDto(savedTask);
    }


    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

   /* public List<Task> getTaskByUserId(Long userid){
        return taskRepository.findByUserId(userid);
    }*/

    public void delete(Long id) {
        if(!taskRepository.existsById(id)){
            throw new RuntimeException("Задача по id: " + id + "не найдена" );
        }
        taskRepository.deleteById(id);
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto updatedTaskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));

        task.setTitle(updatedTaskDto.getTitle());
        task.setDescription(updatedTaskDto.getDescription());
        task.setTaskStatus(updatedTaskDto.getTaskStatus());
        task.setTaskPriority(updatedTaskDto.getTaskPriority());
        task.setDueDate(updatedTaskDto.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);
        return taskMapper.toResponseDto(saved);
    }

    public List<TaskResponseDto> getTasksByStatus(Status status) {
        List<Task> tasks = taskRepository.findAllByStatus(status);
        if (tasks.isEmpty()) {
            throw new NotFoundException("No tasks with status " + status);
        }
        return tasks.stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }

    public List<TaskResponseDto> getTasksByPriority(Priority priority) {
        List<Task> priorities = taskRepository.findAllByTaskPriority(priority);
        if (priorities.isEmpty()) {
            throw new NotFoundException("No tasks with priority " + priority);
        }
        return priorities.stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }

    public List<TaskResponseDto> getTasksByAssignee(Long assigneeId) {
        List<Task> tasks = taskRepository.findAllByAssigneeId(assigneeId);
        if (tasks.isEmpty()) {
            throw new NotFoundException("No tasks for assigneeId " + assigneeId);
        }
        return tasks.stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }
    public List<TaskResponseDto> getOverdueTasks(LocalDateTime now) {
        List<Task> tasks = taskRepository.findAllByDueDateBefore(now);
        if (tasks.isEmpty()) {
            throw new NotFoundException("No overdue tasks");
        }
        return tasks.stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }
    public List<TaskResponseDto> getTasksByStatusAndAssignee(Status status, Long assigneeId) {
        List<Task> tasks = taskRepository.findAllByStatusAndAssigneeId(status, assigneeId);
        if (tasks.isEmpty()) {
            throw new NotFoundException("No tasks with status " + status + " for assigneeId " + assigneeId);
        }
        return tasks.stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }


}

