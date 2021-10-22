package com.arianahmadifard.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.arianahmadifard.task.R;
import com.arianahmadifard.task.databinding.ItemBinding;
import com.arianahmadifard.task.model.TaskResponseItem;
import com.arianahmadifard.task.view.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<TaskResponseItem> items;
    private ItemBinding binding;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ItemBinding.inflate(inflater,parent,false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.itemBinding.tvNameItem.setText(items.get(position).getName());
        holder.itemBinding.tvCountryItem.setText(items.get(position).getCountry());
        holder.itemBinding.btnLike.setChecked(items.get(position).getLikeStatus());
        Picasso.get().load(items.get(position).getPortrait()).error(R.drawable.image_not_found).fit().into(holder.itemBinding.ivPortItem);
        String date = items.get(position).getUpdatedDate().toString();
        holder.itemBinding.tvDateItem.setText(date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetails = new Intent(context, DetailsActivity.class);
                String name = items.get(position).getName();
                String date = String.valueOf(items.get(position).getUpdatedDate());
                String country = items.get(position).getCountry();
                String description = items.get(position).getDescription();
                String imageUrl = items.get(position).getLandscape();
                boolean isLike = items.get(position).getLikeStatus();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("date",date);
                bundle.putString("country",country);
                bundle.putString("description",description);
                bundle.putString("imageUrl",imageUrl);
                bundle.putBoolean("isLike",isLike);
                intentDetails.putExtras(bundle);
                context.startActivity(intentDetails);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;
        public ItemViewHolder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public ItemAdapter(Context context, List<TaskResponseItem> items) {
        this.context = context;
        this.items = items;
    }

    //TODO Migrate to DiffUtil
    public  void updateList(List<TaskResponseItem> updatedList){
        items = updatedList;
        notifyDataSetChanged();
    }

    public  TaskResponseItem getPokemonAt(int position){
        return items.get(position);
    }
}
