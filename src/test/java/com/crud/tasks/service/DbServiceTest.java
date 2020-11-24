package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldFetchEmptyListTasks() {
        //Given
        taskRepository.deleteAll();
//        when(taskRepository.findAll()).thenReturn(List.of());

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        assertEquals(0, tasks.size());
    }

    @Test
    void shouldFetchEmptyListByMethodfindAllById() {
        //Given
        taskRepository.deleteAll();

        //When
        List<Task> tasks = dbService.getAllTasksById(2L);

        //Then
        assertEquals(0, tasks.size());
    }

    @Test
    void shouldGetAllTasks() {
        //Given
        taskRepository.deleteAll();

        Task task1 = new Task(1L, "test", "test cont");
        Task task2 = new Task(2L, "test2", "test cont2");

        taskRepository.save(task1);

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        assertEquals(1, tasks.size());

        //clean up
        taskRepository.deleteAll();
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task task1 = new Task(1L, "test", "test cont");

        //When
        Task taskSaved = dbService.saveTask(task1);

        //Then
        assertEquals("test", taskSaved.getTitle());
        assertEquals("test cont", taskSaved.getContent());

        //clean up
        taskRepository.deleteAll();
    }

    @Test
    void shouldDeleteTask() {
        //Given
        Task task1 = new Task(1L, "test", "test cont");
        Task tasksSaved = taskRepository.save(task1);

        //When
        dbService.deleteTask(tasksSaved.getId());

        //Then
        assertEquals(0, dbService.getAllTasksById(tasksSaved.getId()).size());

        //clean up
        taskRepository.deleteAll();
    }

    @Test
    void shouldGetTask() {
        //Given
        Task task1 = new Task(1L, "test", "test cont");
        Task tasksSaved = taskRepository.save(task1);

        //When
        Optional<Task> task = dbService.getTask(tasksSaved.getId());

        //Then
        assertTrue(task.isPresent());

        //clean up
        taskRepository.deleteAll();
    }


}