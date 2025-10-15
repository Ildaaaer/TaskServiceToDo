package ildaaaer.service;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.dto.TaskResponseDto;
import ildaaaer.entity.Task;
import ildaaaer.exceptions.BadRequestException;
import ildaaaer.exceptions.NotFoundException;
import ildaaaer.factories.TaskMapper;
import ildaaaer.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                    .map(task -> {
                        task.setTitle(updatedTask.getTitle());
                        task.setDescription(updatedTask.getDescription());
                        task.setStatus(updatedTask.getStatus());
                        task.setTaskPriority(updatedTask.getTaskPriority());
                        task.setDueDate(updatedTask.getDueDate());
                        return taskRepository.save(task);
                    })
                    .orElseThrow(() -> new BadRequestException("Task with id " + id + " not found"));
        }
    }

