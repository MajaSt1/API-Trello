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
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private TaskFacade taskFacade;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() { return taskFacade.getDbTasks(); }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam(value = "taskId", required = true) Long taskId) throws TaskNotFoundException {
        return taskFacade.getDbTask(taskId);
    } //*******

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam(value = "taskId", required = true) Long taskId) {
        taskFacade.deleteDbTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestParam(value = "taskId") Long taskId, @RequestBody TaskDto taskDto) {
        return taskFacade.updateDbTask(taskId,taskDto);
    }

    @RequestMapping(method=RequestMethod.POST,value = "createTask",consumes = APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody TaskDto taskDto){
        return taskFacade.createDbTask(taskDto);
    }
}


