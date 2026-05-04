package ildaaaer.controllers;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.dto.TaskResponseDto;
import ildaaaer.entity.Task;
import ildaaaer.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto responseDto = taskService.createTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponseDto>> fetchTask(@RequestParam(name = "keyword") String keywords) {
        List<TaskResponseDto> tasks = taskService.fetchTasks(keywords);

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequestDto updateTaskDto
    ) {
        TaskResponseDto updatedTask = taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.ok(updatedTask);
    }


}
