package com.arianahmadifard.task.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arianahmadifard.task.model.TaskResponse;
import com.arianahmadifard.task.model.TaskResponseItem;
import com.arianahmadifard.task.repository.database.TaskRepository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;


@HiltViewModel
public class MainViewModel extends ViewModel {
    TaskRepository repository;
    MutableLiveData<List<TaskResponseItem>> items = new MutableLiveData<>();
    public static int pageNumber = 0;
    static List<TaskResponseItem> dataset = new ArrayList<>();
    public static List<TaskResponseItem> currentList = new ArrayList<>();
    public static boolean refresh = false;
    @Inject
    public MainViewModel(TaskRepository repository) {

        this.repository = repository;
    }

    public LiveData<List<TaskResponseItem>> getItems() {
        getItemsFromDB();
        if (dataset.size() == 0) {
            getItemsFromNetwork();
        } else {
            int pageSize = 10;
            pageNumber++;
            if (pageNumber >= 4) {
                return items;
            }
//            List<TaskResponseItem> list1 = new ArrayList<TaskResponseItem>(dataset.subList((pageNumber - 1) * pageSize, pageNumber * pageSize));
            List<TaskResponseItem> list1 = new ArrayList<TaskResponseItem>(dataset.subList(0, pageNumber * pageSize));
            pageNumber++;
            List<TaskResponseItem> list2 = new ArrayList<TaskResponseItem>(dataset.subList((pageNumber - 1) * pageSize, pageNumber * pageSize));
            currentList.addAll(merger(list1, list2));
            items.setValue(currentList);

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
                        dataset = taskResponse.getData();
                        insertAllItemsToDB();
                        int pageSize = 10;
                        pageNumber++;
                        List<TaskResponseItem> list1 = new ArrayList<TaskResponseItem>(dataset.subList((pageNumber - 1) * pageSize, pageNumber * pageSize));
                        pageNumber++;
                        List<TaskResponseItem> list2 = new ArrayList<TaskResponseItem>(dataset.subList((pageNumber - 1) * pageSize, pageNumber * pageSize));
                        currentList.addAll(merger(list1, list2));

                        items.setValue(currentList);
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
        dataset = repository.getItemsFromDB();
    }

    public void insertAllItemsToDB() {
        repository.insertAll(dataset);
    }

    public  void refresher() {
        pageNumber = 0;
        items.setValue(null);
        currentList = new ArrayList<>();
        repository.taskDao.deleteAll();
        getItemsFromNetwork();
    }


    public static List<TaskResponseItem> merger(List<TaskResponseItem> list1, List<TaskResponseItem> list2) {
        Hashtable<String, TaskResponseItem> dict = new Hashtable<String, TaskResponseItem>();
        list1.addAll(list2); // join to list together
        for (TaskResponseItem item : list1) {
            TaskResponseItem obj = dict.get(item.getGuid());
            if (obj == null)
                dict.put(item.getGuid(), item);
            else if (item.getUpdatedDate() > obj.getUpdatedDate()) {
                dict.put(item.getGuid(), item);
            } else { // uncomment to show removed items
                System.out.println(item.getGuid() + " **** " + item.getGuid());
            }
        }
        return new ArrayList<TaskResponseItem>(dict.values());
    }


}
