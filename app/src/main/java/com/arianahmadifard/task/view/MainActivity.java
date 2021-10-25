package com.arianahmadifard.task.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.arianahmadifard.task.R;
import com.arianahmadifard.task.adapter.ItemAdapter;
import com.arianahmadifard.task.databinding.ActivityMainBinding;
import com.arianahmadifard.task.model.TaskResponseItem;
import com.arianahmadifard.task.repository.database.TaskRepository;
import com.arianahmadifard.task.viewmodel.MainViewModel;
import com.arianahmadifard.task.viewmodel.MyViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.processors.PublishProcessor;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
     MainViewModel viewModel;
    @Inject
    TaskRepository taskRepository;
    int totalItemCount,lastVisibleItem;
    GridLayoutManager layoutManager;
    boolean loading=false;
    public static boolean isMoreItemLoaded=false;
    public static ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater(  ));
        View view = binding.getRoot();
        setContentView(view);
        binding.pbItems.setVisibility(View.VISIBLE);
        viewModel=new ViewModelProvider
                (this, new MyViewModelFactory(this.getApplication(),taskRepository)).get(MainViewModel.class);
        viewModel.getItems().observe(this, new Observer<List<TaskResponseItem>>() {
            @Override
            public void onChanged(List<TaskResponseItem> taskResponseItems) {
                if(isMoreItemLoaded)
                {
                    adapter.notifyDataSetChanged();
                }else
                {
                    adapter= new ItemAdapter(MainActivity.this,taskResponseItems);
                    setupRecyclerview(taskResponseItems);
                }

                loading=false;
                binding.pbItems.setVisibility(View.GONE);
                if (binding.refresher.isRefreshing()) {
                    binding.refresher.setRefreshing(false);
                }
            }
        });
        binding.refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresher();
            }
        });
        binding.recyclerviewItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem  = layoutManager.findLastVisibleItemPosition();
                if (totalItemCount<=lastVisibleItem + 2)
                {
//                    MainViewModel.pageNumber++;
                    if (MainViewModel.pageNumber>=4)
                    {
                        return;
                    }
                    binding.pbItems.setVisibility(View.VISIBLE);
                    loading=true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Your Code
                            viewModel.getItems();
                            isMoreItemLoaded=true;

                        }
                    }, 1000);

                }

                Log.i("last", "onScrolled: " + lastVisibleItem);
                Log.i("total", "onScrolled: " + totalItemCount);


            }
        });
    }

    private void setupRecyclerview(List<TaskResponseItem> taskResponseItems) {

        layoutManager = new GridLayoutManager(this,2);
        binding.recyclerviewItems.setLayoutManager(layoutManager);
        binding.recyclerviewItems.setHasFixedSize(true);
        binding.recyclerviewItems.setAdapter(adapter);
    }

}