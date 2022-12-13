package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void validTaskExistShouldntThrowException() {
        //Given
        final long id = 9;
        when(repository.existsById(id)).thenReturn(true);

        //When
        assertDoesNotThrow(() -> dbService.validTaskExist(id));

        //Then
        verify(repository, times(1)).existsById(id);
    }

    @Test
    public void validTaskExistShouldThrowException() {
        //Given
        final long id = 10;
        when(repository.existsById(id)).thenReturn(false);

        //When
        assertThrows(TaskNotFoundException.class, () -> dbService.validTaskExist(id));

        //Then
        verify(repository, times(1)).existsById(id);
    }

    @Test
    public void deleteTaskShouldValidateTaskAndRemove() throws TaskNotFoundException {
        //Given
        final long id = 10;
        when(repository.existsById(id)).thenReturn(true);

        //When
        dbService.deleteTask(id);

        //Then
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deleteTaskShouldOnlyThrowException() {
        //Given
        final long id = 10;
        when(repository.existsById(id)).thenReturn(false);

        //When
        assertThrows(TaskNotFoundException.class, () -> dbService.deleteTask(id));

        //Then
        verify(repository, times(1)).existsById(id);
        verify(repository, times(0)).deleteById(id);
    }

    @Test
    public void updateTaskShouldValidateTaskAndUpdate() throws TaskNotFoundException {
        //Given
        final long id = 10;
        Task task = Task.builder()
                .id(id)
                .build();

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(task)).thenReturn(task);
        //When
        Task resultTask = dbService.updateTask(task);

        //Then
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(resultTask);
        assertEquals(task, resultTask);
    }

    @Test
    public void updateTaskShouldOnlyThrowException() {
        //Given
        final long id = 10;
        Task task = Task.builder()
                .id(id)
                .build();
        when(repository.existsById(id)).thenReturn(false);

        //When
        assertThrows(TaskNotFoundException.class, () -> dbService.updateTask(task));

        //Then
        verify(repository, times(1)).existsById(id);
        verify(repository, times(0)).save(task);
    }
}
