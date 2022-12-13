package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    private static final String API_PATH = "/v1/tasks";

    private static final Gson GSON = new Gson();

    private static final long ID_VALUE = 10;

    private static final String TITLE_VALUE = "titleValue";

    private static final String CONTENT_VALUE = "contentValue";

    private static Task task;

    private static TaskDto taskDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @BeforeAll
    static void up() {
        task = Task.builder()
                .id(ID_VALUE)
                .title(TITLE_VALUE)
                .content(CONTENT_VALUE)
                .build();

        taskDto = TaskDto.builder()
                .id(ID_VALUE)
                .title(TITLE_VALUE)
                .content(CONTENT_VALUE)
                .build();
    }

    @Test
    void shouldReturnListWithOneTask() throws Exception {
        // Given
        List<Task> tasks = List.of(task);
        List<TaskDto> tasksDto = List.of(taskDto);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is((int) ID_VALUE)))
                .andExpect(jsonPath("$[0].title", Matchers.is(TITLE_VALUE)))
                .andExpect(jsonPath("$[0].content", Matchers.is(CONTENT_VALUE)));
    }

    @Test
    void shouldReturnSelectedTask() throws Exception {
        // Given
        when(service.getTask(ID_VALUE)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(API_PATH + "/" + ID_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));
        expectValueOfExampleDto(resultActions);
    }

    @Test
    void shouldDeleteTask() throws Exception {
        // Given

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(API_PATH + "/" + ID_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$").doesNotExist());
        verify(service, times(1)).deleteTask(ID_VALUE);
    }

    @Test
    void shouldUpdateTask() throws Exception {
        // Given
        String jsonContent = GSON.toJson(taskDto);

        when(service.updateTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put(API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
        );
        expectValueOfExampleDto(resultActions);
        verify(service, times(1)).updateTask(task);
    }

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        String jsonContent = GSON.toJson(taskDto);

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                )
                .andExpect(jsonPath("$").doesNotExist());
        verify(service, times(1)).saveTask(task);
    }

    private void expectValueOfExampleDto(ResultActions resultAction) throws Exception {
        resultAction
                .andExpect(jsonPath("$.id", Matchers.is((int) ID_VALUE)))
                .andExpect(jsonPath("$.title", Matchers.is(TITLE_VALUE)))
                .andExpect(jsonPath("$.content", Matchers.is(CONTENT_VALUE)));
    }
}
