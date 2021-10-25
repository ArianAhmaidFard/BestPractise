package com.arianahmadifard.task.viewmodel;

import androidx.annotation.IntegerRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arianahmadifard.task.repository.database.TaskRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailsViewModel extends ViewModel {
    TaskRepository repository;
    MutableLiveData<Boolean> likeState =new MutableLiveData<>();
    @Inject
    public DetailsViewModel(TaskRepository repository) {
        this.repository = repository;
    }
    public void like(boolean checked,String  id)
    {
        repository.taskDao.update(checked,id);
    }
}
