package com.arianahmadifard.task.viewmodel;

import android.app.Application;

import androidx.annotation.IntegerRes;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.arianahmadifard.task.repository.database.TaskRepository;

import javax.inject.Inject;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    @Inject
    public TaskRepository repository;


    public MyViewModelFactory(Application application,TaskRepository repository2) {
        mApplication = application;
        repository = repository2;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainViewModel(repository);
    }
}