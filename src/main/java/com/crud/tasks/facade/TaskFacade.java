package com.crud.tasks.facade;

import com.crud.tasks.controller.TaskNoFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskFacade {

    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper mapper;

    public List<TaskDto> getTasks(){
        return mapper.mapToTaskDtoList(service.getAllTasks());
    }

    public TaskDto getTask(Long taskId) throws TaskNoFoundException{
        return mapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNoFoundException::new));
    }

    public void deleteTask(Long taskId) throws TaskNoFoundException{
        service.deleteTask(service.getTask(taskId).orElseThrow(TaskNoFoundException::new));
    }

    public TaskDto updateTask(TaskDto taskDto){
        return mapper.mapToTaskDto(service.saveTask(mapper.mapToTask(taskDto)));
    }

    public void createTask(TaskDto taskDto){
        service.saveTask(mapper.mapToTask(taskDto));
    }
}
