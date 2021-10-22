package com.arianahmadifard.task.repository.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.arianahmadifard.task.model.TaskResponseItem;

@Database(entities = {TaskResponseItem.class},version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
