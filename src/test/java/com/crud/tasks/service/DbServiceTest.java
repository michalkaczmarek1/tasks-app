package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldFetchEmptyListTasks() {
        //Given
        taskRepository.deleteAll();

        //When
        List<Task> tasks = taskRepository.findAll();

        //Then
        assertEquals(0, tasks.size());
    }

    @Test
    void shouldFetchEmptyListByMethodfindAllById() {
        //Given
        taskRepository.deleteAll();

        //When
        List<Task> tasks = taskRepository.findAllById(2L);

        //Then
        assertEquals(0, tasks.size());
    }


}