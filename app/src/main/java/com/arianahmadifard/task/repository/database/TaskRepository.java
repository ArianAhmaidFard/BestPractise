package com.arianahmadifard.task.repository.database;

import com.arianahmadifard.task.model.TaskResponse;
import com.arianahmadifard.task.model.TaskResponseItem;
import com.arianahmadifard.task.repository.network.TaskService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class TaskRepository {
    public TaskService taskService;
    public TaskDao taskDao;
    @Inject
    public TaskRepository(TaskService taskService,TaskDao taskDao) {
        this.taskService = taskService;
        this.taskDao = taskDao;
    }

    public Observable<TaskResponse> getItemsFromNetwork(){
        return taskService.getItems();
    }
    public void insertAll(List<TaskResponseItem> items)
    {
        taskDao.insertAll(items);
    }

    public List<TaskResponseItem> getItemsFromDB() {
        return taskDao.getAll();
    }
}
