package com.example.m3material_app.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3material_app.ListenerOnclick;
import com.example.m3material_app.R;
import com.example.m3material_app.models.ComponentModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<ComponentModel> arrayListAdapter = new ArrayList<>();
    ListenerOnclick listenerOnClick;


    public RecyclerAdapter(ArrayList<ComponentModel> structureClassArrayList, ListenerOnclick listenerOnClick) {

        this.arrayListAdapter = structureClassArrayList;
        this.listenerOnClick= listenerOnClick;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false));

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imgBtn.setImageResource(arrayListAdapter.get(position).btn);
        holder.text.setText((arrayListAdapter.get(position).name));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listenerOnClick!=null){
                    listenerOnClick.onClickListener(position);
//                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return arrayListAdapter.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
                TextView text;
                ImageView imgBtn;
                CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);
            text = itemView.findViewById(R.id.text2);
            imgBtn = itemView.findViewById(R.id.img1);
            cardView =itemView.findViewById(R.id.cardView1);



        }


    }




}
