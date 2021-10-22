package com.arianahmadifard.task.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arianahmadifard.task.model.TaskResponse;
import com.arianahmadifard.task.model.TaskResponseItem;
import com.arianahmadifard.task.repository.database.TaskDatabase;
import com.arianahmadifard.task.repository.database.TaskRepository;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;


@HiltViewModel
public class MainViewModel extends ViewModel {
    TaskRepository repository;
    MutableLiveData<List<TaskResponseItem>> items = new MutableLiveData<>();
    @Inject
    public MainViewModel(TaskRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<TaskResponseItem>> getItems()
    {
        getItemsFromDB();

        if(items.getValue().size()==0)
        {
            getItemsFromNetwork();
        }
        else {
            Log.i("DB_ID", "id-10: " + items.getValue().get(10).getId());
        }
        return items;
    }
    public void getItemsFromNetwork() {

        repository.getItemsFromNetwork().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskResponse>() {
                    String error; // todo check errors
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TaskResponse taskResponse) {
                        items.setValue(taskResponse.getData());
                        Log.i("DB_ID", "id-11: " + items.getValue().get(11).getId());
                        insertAllItemsToDB();
                        Log.i("DB_ID", "id-12: " + items.getValue().get(12).getId());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        items.setValue(null);
                        if (e instanceof HttpException) {

                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getItemsFromDB() {

        items.setValue(repository.getItemsFromDB());
    }
    public void insertAllItemsToDB()
    {

        repository.insertAll(items.getValue());

    }
    public void refresher() {
        repository.taskDao.deleteAll();
        getItemsFromNetwork();
    }

    public static List<TaskResponseItem> compareList(List<TaskResponseItem> l1, List<TaskResponseItem>  l2) {


        return new ArrayList<>();
    }

    public TaskResponseItem compareData(long firstData, long secondData) {
        return new TaskResponseItem();
    }


}
