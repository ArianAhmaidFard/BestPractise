package com.arianahmadifard.task.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arianahmadifard.task.R;
import com.arianahmadifard.task.databinding.ActivityDetailesBinding;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailesBinding binding;
    String name,description,country,imageUrl;
    boolean isLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityDetailesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        if (extras!=null)
        {
            name = extras.getString("name");
            country = extras.getString("country");
            description = extras.getString("description");
            imageUrl = extras.getString("imageUrl");
            isLike = extras.getBoolean("isLike");
            initView();
        }
    }

    private void initView() {
        binding.tvNameItemDetails.setText(name);
        binding.tvCountryItemDetails.setText(country);
        binding.tvDescriptionItemDetails.setText(description);
        binding.btnLikeItemDetails.setChecked(isLike);
        Picasso.get().load(imageUrl).error(R.drawable.image_not_found).into(binding.ivLansdcapeItemDetails);

    }
}