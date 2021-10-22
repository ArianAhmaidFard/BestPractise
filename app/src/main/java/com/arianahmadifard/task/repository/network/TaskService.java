package com.arianahmadifard.task.repository.network;

import com.arianahmadifard.task.model.TaskResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface TaskService {

    @GET(Urls.TEST)
    Observable<TaskResponse> getItems();
}
