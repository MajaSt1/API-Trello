package com.crud.tasks.mapper.task;

import com.crud.tasks.TasksApplication;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTasktest(){
        TaskDto taskDto= new TaskDto(1L,"task","taskToDo");
        //
        Task task= taskMapper.mapToTask(taskDto);
        //
        assertNotNull(task);
        assertEquals("taskToDo",task.getContent());
    }
    @Test
    public void mapToTaskDto(){
        Task task= new Task(1L,"taskDto","taskDtoToDo");
        //
        TaskDto taskDto= taskMapper.mapToTaskDto(task);
        //
        assertNotNull(task);
        assertEquals("taskDtoToDo",task.getContent());
    }
    @Test
    public void mapToTaskDtoList(){
        List<Task> tasks= new ArrayList<>();
        tasks.add(new Task(1L,"task","taskToDo"));
        //
        List<TaskDto> taskDtos= taskMapper.mapToTaskDtoList(tasks);
        //
        assertNotNull(taskDtos);
        assertEquals(1,taskDtos.size());
    }
}
