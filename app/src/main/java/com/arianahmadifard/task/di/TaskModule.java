package com.arianahmadifard.task.di;


import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.arianahmadifard.task.repository.database.TaskDao;
import com.arianahmadifard.task.repository.database.TaskDatabase;
import com.arianahmadifard.task.repository.network.TaskService;
import com.arianahmadifard.task.repository.network.Urls;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




@Module
@InstallIn(SingletonComponent.class)
public abstract class TaskModule {

    //provide room database
    @Singleton
    @Provides
    public static TaskDatabase provideDB(Application application)
    {
        return Room.databaseBuilder(application, TaskDatabase.class,"database-task")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public static TaskDao provideTaskDao(TaskDatabase taskDatabase)
    {
        return taskDatabase.taskDao();
    }


    //get instance from retrofit with rxjava3
    @Singleton
    @Provides
    public static Retrofit provideRetrofitInstance()
    {
        return new Retrofit.Builder().baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // rxjava3 used here
                .build();
    }


    @Singleton
    @Provides
    public static TaskService provideTaskService(Retrofit retrofit)
    {
        return retrofit.create(TaskService.class);
    }
}
