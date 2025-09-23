package ildaaaer.service;

import ildaaaer.entity.Task;
import ildaaaer.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task createTask(Task task){
        if(taskRepository.findByTitle(task.getTitle()) != null){
            throw new RuntimeException("Задача уже существует");
        }
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }


}
