package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void shouldFetchEmptyList() {
        //Given
        List<Task> tasks = new ArrayList<>();

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(0, taskDtos.size());
    }

    @Test
    void shouldMapToTaskDtoList() {
        //Given
        List<Task> taskList = List.of(new Task(1L, "Test", "Test desc"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, taskDtoList.size());
        assertEquals("Test", taskDtoList.get(0).getTitle());
        assertEquals("Test desc", taskDtoList.get(0).getContent());
    }

    @Test
    void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(2L, "Title", "Content desc");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(2L, task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("Content desc", task.getContent());
    }

    @Test
    void shouldMapToTaskDto() {
        //Given
        Task task = new Task(2L, "Title", "Content desc");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(2L, taskDto.getId());
        assertEquals("Title", taskDto.getTitle());
        assertEquals("Content desc", taskDto.getContent());
    }




}