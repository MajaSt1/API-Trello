package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskByID(final Long taskId){
        return taskRepository.findById(taskId); // .orElse(null);
    }
    public Task saveTask(final Task task){
        return taskRepository.save(task);
    }
    }
