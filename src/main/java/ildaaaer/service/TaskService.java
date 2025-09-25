package ildaaaer.service;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.entity.Task;
import ildaaaer.exceptions.BadRequestException;
import ildaaaer.factories.TaskRequestDtoFactory;
import ildaaaer.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private TaskRequestDtoFactory taskRequestDtoFactory;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public TaskRequestDto createTask(Task task){
        taskRepository.findByTitle(task.getTitle())
                .ifPresent(t -> {
                    throw new BadRequestException("Task already exists");
                });
        task = taskRepository.saveAndFlush(task);
        return taskRequestDtoFactory.makeTaskRequestDTO(task);
    }


    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTaskByUserId(Long userid){
        return taskRepository.findByUserId(userid);
    }

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
                    .orElseThrow(() -> new RuntimeException("Задача по id: " + id + "не найдена"));
        }
    }

