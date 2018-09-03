package com.crud.tasks.trello.facade;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class TaskFacade {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDto> getDbTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    public TaskDto getDbTask(final Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTaskByID(taskId).orElseThrow(TaskNotFoundException::new)); }

    public void deleteDbTask(final Long taskId) { service.deleteTaskById(taskId); }

    public TaskDto updateDbTask(final Long taskId,final TaskDto taskDto) {
        taskDto.setId(taskId);
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto))); }

    public Task createDbTask(final TaskDto taskDto){ return service.saveTask (taskMapper.mapToTask ( taskDto)); }
}

