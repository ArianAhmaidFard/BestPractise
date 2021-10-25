package com.arianahmadifard.task.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.arianahmadifard.task.R;
import com.arianahmadifard.task.databinding.ActivityDetailesBinding;
import com.arianahmadifard.task.viewmodel.DetailsViewModel;
import com.arianahmadifard.task.viewmodel.MainViewModel;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {
    ActivityDetailesBinding binding;
    String name,description,country,imageUrl,guid;
    boolean isLike;
    int pos;
    DetailsViewModel viewModel;
    boolean refresh =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityDetailesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        Bundle extras = getIntent().getExtras();
        if (extras!=null)
        {
            guid = extras.getString("guid");
            pos = extras.getInt("pos");
            name = extras.getString("name");
            country = extras.getString("country");
            description = extras.getString("description");
            imageUrl = extras.getString("imageUrl");
            isLike = extras.getBoolean("isLike");
            initView();
        }
        binding.btnLikeItemDetails.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                viewModel.like(checked,guid);
                for (int i = 0; i <MainViewModel.currentList.size() ; i++) {
                    if (guid.equals(MainViewModel.currentList.get(i).getGuid()))
                    {
                        MainViewModel.currentList.get(i).setLikeStatus(checked);
                    }
                }
                refresh=true;
            }
        });
    }
    private void initView() {
        binding.tvNameItemDetails.setText(name);
        binding.tvCountryItemDetails.setText(country);
        binding.tvDescriptionItemDetails.setText(description);
        binding.btnLikeItemDetails.setChecked(isLike);
        Picasso.get().load(imageUrl).error(R.drawable.image_not_found).into(binding.ivLansdcapeItemDetails);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (refresh)
        {
            //refresh dataset
            MainActivity.adapter.notifyItemChanged(pos);
            MainActivity.adapter.notifyDataSetChanged();
        }

    }
}