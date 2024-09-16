package com.example.m3material_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3material_app.R;
import com.example.m3material_app.activities.CarouselActivity;
import com.example.m3material_app.models.CarouselModel;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    Context context;
    ArrayList<CarouselModel> carouselarrayList;

    public ImageAdapter(ArrayList<CarouselModel> carouselarrayList, CarouselActivity carouselActivity) {
        this.carouselarrayList = carouselarrayList;
        this.context = carouselActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carsouel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(carouselarrayList.get(position).img);
    }

    @Override
    public int getItemCount() {
        return carouselarrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

                ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.list_item_image);

        }


    }
}
