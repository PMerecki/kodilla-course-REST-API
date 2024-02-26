package com.crud.tasks.coverage;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskController = new TaskController(dbService, taskMapper);
    }

    @Test
    void getTasks_shouldReturnEmptyList_whenNoTasks() {
        // Given
        when(dbService.getAllTasks()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<TaskDto>> responseEntity = taskController.getTasks();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).isEmpty());
    }

    @Test
    void getTasks_shouldReturnTaskList_whenTasksExist() {
        // Given
        List<Task> tasks = Collections.singletonList(new Task(1L, "Task 1", "Content 1"));
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(Collections.singletonList(new TaskDto(1L, "Task 1", "Content 1")));

        // When
        ResponseEntity<List<TaskDto>> responseEntity = taskController.getTasks();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(Objects.requireNonNull(responseEntity.getBody()).isEmpty());
        assertEquals(tasks.size(), responseEntity.getBody().size());
    }

    @Test
    void getTask_shouldReturnTask_whenTaskExists() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Task 1", "Content 1");
        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(new TaskDto(1L, "Task 1", "Content 1"));

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.getTask(1L);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(task.getId(), responseEntity.getBody().getId());
    }

    @Test
    void getTask_shouldReturnNotFound_whenTaskDoesNotExist() throws TaskNotFoundException {
        // Given
        when(dbService.getTask(1L)).thenThrow(TaskNotFoundException.class);

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.getTask(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Task with given id doesn't exist", responseEntity.getBody().getContent());
    }


    @Test
    void deleteTask_shouldReturnOk_whenTaskIsDeleted() {
        // Given

        // When
        ResponseEntity<Void> responseEntity = taskController.deleteTask(1L);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateTask_shouldReturnUpdatedTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Content");
        Task task = new Task(1L, "Task", "Content");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.updateTask(taskDto);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDto, responseEntity.getBody());
    }

    @Test
    void createTask_shouldReturnOk_whenTaskIsCreated() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "New Task", "New Content");
        Task task = new Task(1L, "New Task", "New Content");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When
        ResponseEntity<Void> responseEntity = taskController.createTask(taskDto);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getTask_TaskNotFoundException_ReturnsNotFound() throws TaskNotFoundException {
        // Given
        when(dbService.getTask(anyLong())).thenThrow(TaskNotFoundException.class);

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.getTask(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        TaskDto responseBody = responseEntity.getBody();
        System.out.println("Response body: " + responseBody); // Wyświetlenie treści odpowiedzi w celu debugowania
        assertEquals("Task with given id doesn't exist", responseBody.getContent());
    }
}

