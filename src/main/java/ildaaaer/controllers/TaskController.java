package ildaaaer.controllers;

import ildaaaer.dto.TaskRequestDto;
import ildaaaer.dto.TaskResponseDto;
import ildaaaer.service.TaskService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
