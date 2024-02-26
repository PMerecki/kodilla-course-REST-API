package com.crud.tasks.coverage;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTasksTest() {
        // Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Task 1", "Content 1"));
        taskList.add(new Task(2L, "Task 2", "Content 2"));
        when(taskRepository.findAll()).thenReturn(taskList);

        // When
        List<Task> retrievedTasks = dbService.getAllTasks();

        // Then
        assertEquals(taskList.size(), retrievedTasks.size());
        for (int i = 0; i < taskList.size(); i++) {
            assertEquals(taskList.get(i), retrievedTasks.get(i));
        }
    }

    @Test
    public void getTaskTest() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Task 1", "Content 1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Task retrievedTask = dbService.getTask(1L);

        // Then
        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void getTask_NotFound_Test() {
        // Given
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(1L));
    }

    @Test
    public void saveTaskTest() {
        // Given
        Task task = new Task(1L, "Task 1", "Content 1");
        when(taskRepository.save(task)).thenReturn(task);

        // When
        Task savedTask = dbService.saveTask(task);

        // Then
        assertNotNull(savedTask);
        assertEquals(task, savedTask);
    }

    @Test
    public void deleteTaskTest() {
        // Given, When
        dbService.deleteTask(1L);

        // Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}

