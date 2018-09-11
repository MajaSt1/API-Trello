package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TaskFacade;
import com.google.gson.Gson;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Matchers.any;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskFacade taskFacade;


    @Test
    public void getTasksTest() throws Exception{
        //Given
        List<TaskDto> taskDtos= new ArrayList<>();
        taskDtos.add(new TaskDto(1L,"task","task"));

        when(taskFacade.getDbTasks()).thenReturn(taskDtos);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("task")))
                .andExpect(jsonPath("$[0].content",is("task")));
    }
    @Test
    public void getTaskTest() throws Exception{
        //Given
        TaskDto taskDto= new TaskDto(1L,"task","task");

        when(taskFacade.getDbTask(anyLong())).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("task")))
                .andExpect(jsonPath("$.content",is("task")));

    }
    @Test
    public void deleteTaskTest() throws Exception {
        //
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/v1/task/deleteTask?taskId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void updateTaskTest() throws Exception{
        //
        TaskDto taskDto= new TaskDto(1L,"task","task");

        when(taskFacade.updateDbTask(anyLong(),any(TaskDto.class))).thenReturn(taskDto);
        //
        Gson gson=new Gson();
        String jsonContent= gson.toJson(taskDto);

        mockMvc.perform(put("/v1/task/updateTask?taskId=1").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("task")))
                .andExpect(jsonPath("$.content",is("task")));

    }
    @Test
    public void createTaskTest() throws Exception{
        //Given
        Task createdTask = new Task (1L,"task","task");
        TaskDto taskDto= new TaskDto(1L,"task","task");

        when(taskFacade.createDbTask(any(TaskDto.class))).thenReturn(createdTask);

        Gson gson=new Gson();
        String jsonContent= gson.toJson(taskDto);
        //When&Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)) //!
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title", is("task")))
                .andExpect(jsonPath("$.content",is("task")));
    }


}
