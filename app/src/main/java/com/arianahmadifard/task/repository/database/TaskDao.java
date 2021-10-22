package com.arianahmadifard.task.repository.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arianahmadifard.task.model.TaskResponse;
import com.arianahmadifard.task.model.TaskResponseItem;

import java.util.List;


@Dao
public interface TaskDao {

    @Query("SELECT * FROM TASK")
    List<TaskResponseItem> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TaskResponseItem> order);

    @Delete
    void delete(TaskResponseItem user);

    @Query("Delete FROM TASK")
    void deleteAll();
}
