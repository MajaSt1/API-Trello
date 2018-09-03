package com.crud.tasks.facade;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TaskFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TaskFacadeTest {
    @InjectMocks
    private TaskFacade taskFacade;
    @Mock
    private TaskMapper taskMapper;
    @Mock
    private DbService dbService;

    @Test
    public void getDbTasks(){
        //Given
        List<TaskDto> taskDtos= new ArrayList<>();
        taskDtos.add(new TaskDto(1L,"task","task"));
        List<Task> tasks= new ArrayList<>();
        tasks.add(new Task(1L,"task","task"));

        when(taskFacade.getDbTasks()).thenReturn(taskDtos);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtos);
        when(dbService.getAllTasks()).thenReturn(tasks);
        //When
        List<TaskDto> taskDtos1= taskFacade.getDbTasks();
        //Then
        assertNotNull(taskDtos1);
        assertEquals(1,taskDtos1.size());

    }
    @Test
    public void createDbTask(){
        //
        TaskDto taskDto= new TaskDto(1L,"task","task");
        Task task= new Task(1L,"task","task");

        when(taskFacade.createDbTask(taskDto)).thenReturn(task);
        //
        Task task1= taskFacade.createDbTask(taskDto);
        //
        assertNotNull(task1);

    }
}
