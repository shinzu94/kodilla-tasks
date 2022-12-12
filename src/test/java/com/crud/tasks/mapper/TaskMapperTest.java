package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testTrelloTaskDtoToTask() {
        //GIVEN
        TaskDto taskDto = TaskDto.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        Task expectedTask = Task.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        //WHEN
        Task task = taskMapper.mapToTask(taskDto);

        //THEN
        assertEquals(expectedTask, task);
        assertEquals(expectedTask.getId(), task.getId());
        assertEquals(expectedTask.getTitle(), task.getTitle());
        assertEquals(expectedTask.getContent(), task.getContent());
    }

    @Test
    void testTrelloTaskToTaskDto() {
        //GIVEN
        Task task = Task.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        TaskDto expectedTask = TaskDto.builder()
                .id(1L)
                .title("title")
                .content("content")
                .build();

        //WHEN
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //THEN
        assertEquals(expectedTask, taskDto);
        assertEquals(expectedTask.getId(), taskDto.getId());
        assertEquals(expectedTask.getTitle(), taskDto.getTitle());
        assertEquals(expectedTask.getContent(), taskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //GIVEN
        List<Task> tasks = List.of(
                Task.builder()
                        .id(1L)
                        .title("title")
                        .content("content")
                        .build(),
                Task.builder()
                        .id(2L)
                        .title("title")
                        .content("content")
                        .build()
        );

        List<TaskDto> expectedTaskDtos = List.of(
                TaskDto.builder()
                        .id(1L)
                        .title("title")
                        .content("content")
                        .build(),
                TaskDto.builder()
                        .id(2L)
                        .title("title")
                        .content("content")
                        .build()
        );

        //WHEN
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //THEN
        assertEquals(2, taskDtoList.size());
        assertEquals(expectedTaskDtos, taskDtoList);
    }
}
