package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TaskFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class TaskController {

    @Autowired
    private TaskFacade taskFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDto> getTasks() { return taskFacade.getDbTasks(); }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskFacade.getDbTask(taskId);
    } //*******

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskFacade.deleteDbTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask( @RequestBody TaskDto taskDto) {
        return taskFacade.updateDbTask(taskDto);
    }

    @RequestMapping(method=RequestMethod.POST,value = "/tasks",consumes = APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody TaskDto taskDto){
        return taskFacade.createDbTask(taskDto);
    }
}


