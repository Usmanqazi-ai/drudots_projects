package com.example.express.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.NotifactionModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class NotifactionAdapter extends RecyclerView.Adapter<NotifactionAdapter.ViewHolder>{

    ArrayList<NotifactionModel> notifactionModelArrayList ;
    ItemClickListener itemClickListener;

    public NotifactionAdapter(ArrayList<NotifactionModel> notifactionModelArrayList, ItemClickListener itemClickListener) {
        this.notifactionModelArrayList = notifactionModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notifactions,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.message.setText(notifactionModelArrayList.get(position).getMassege());
        holder.time.setText(notifactionModelArrayList.get(position).getTime());
        holder.bind(position,itemClickListener);

    }

    @Override
    public int getItemCount() {
        return notifactionModelArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
            TextView message;
            TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.messageNotification);
            time = itemView.findViewById(R.id.timeNotification);
        }


        public void bind(int position, ItemClickListener itemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position);
                }
            });
        }
    }
}
