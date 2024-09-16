package com.example.express.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.ActiveReportsModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class ActiveReportsAdapter extends RecyclerView.Adapter<ActiveReportsAdapter.ViewHolder> {

    ArrayList<ActiveReportsModel> activeReportsFragmentArrayList = new ArrayList<>();
    ItemClickListener itemClickListener;

    public ActiveReportsAdapter(ArrayList<ActiveReportsModel> activeReportsFragmentArrayList, ItemClickListener itemClickListener) {
        this.activeReportsFragmentArrayList = activeReportsFragmentArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.complainNum.setText(activeReportsFragmentArrayList.get(position).getComplainNum());
        holder.status.setText(activeReportsFragmentArrayList.get(position).getStatus());
        holder.clickAble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return activeReportsFragmentArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView status;
        TextView complainNum;
        TextView clickAble;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status=itemView.findViewById(R.id.txtStatus);
            complainNum = itemView.findViewById(R.id.txtComplnNum);
            clickAble = itemView.findViewById(R.id.txtClick);
        }
    }
}
