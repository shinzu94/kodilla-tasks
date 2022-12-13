package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMapper {

    public Task mapToTask(final TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .content(taskDto.getContent())
                .build();
    }

    public TaskDto mapToTaskDto(final Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .content(task.getContent())
                .build();
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .toList();
    }

}
