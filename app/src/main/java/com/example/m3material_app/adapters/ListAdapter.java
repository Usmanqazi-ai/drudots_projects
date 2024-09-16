package com.example.m3material_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3material_app.R;
import com.example.m3material_app.activities.ListsActivity;
import com.example.m3material_app.models.ListModel;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<ListModel> listModelArrayList = new ArrayList<>();

    public ListAdapter(ArrayList<ListModel> listModelArrayList, ListsActivity listsActivity) {
        this.listModelArrayList = listModelArrayList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
            holder.imageView.setImageResource(listModelArrayList.get(position).img);
            holder.textView.setText(listModelArrayList.get(position).textx);
    }

    @Override
    public int getItemCount() {
        return listModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgList1);
            textView=itemView.findViewById(R.id.txtList1);

        }
    }
}
