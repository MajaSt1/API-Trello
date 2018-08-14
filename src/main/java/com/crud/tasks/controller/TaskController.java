package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }


    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam(value = "id", required = false) Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTaskByID(taskId).orElseThrow(TaskNotFoundException::new));
    } //*******

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam(value = "id", required = false) Long taskId) {
        service.deleteTaskById(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestParam(value = "id") Long taskId, @RequestBody TaskDto taskDto) {
        taskDto.setId(taskId);
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method=RequestMethod.POST,value = "createTask",consumes = APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody TaskDto taskDto){
        return service.saveTask (taskMapper.mapToTask ( taskDto));
    }
}


