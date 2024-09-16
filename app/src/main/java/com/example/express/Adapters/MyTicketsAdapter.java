package com.example.express.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.MyTicketsModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class MyTicketsAdapter  extends RecyclerView.Adapter<MyTicketsAdapter.ViewHolder> {

    ArrayList<MyTicketsModel> myTicketsModelArrayList ;


    ItemClickListener itemClickListener;

    public MyTicketsAdapter(ArrayList<MyTicketsModel> myTicketsModelArrayList, ItemClickListener itemClickListener) {
        this.myTicketsModelArrayList = myTicketsModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_ticket_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.textView1.setText(myTicketsModelArrayList.get(position).getSeatNum());
       holder.textViewName.setText(myTicketsModelArrayList.get(position).getPassengerName());
       holder.bind(position,itemClickListener);

    }

    @Override
    public int getItemCount() {
        return myTicketsModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textViewName;

     public ViewHolder(@NonNull View itemView) {
         super(itemView);
         textView1 = itemView.findViewById(R.id.setNumMyTicket);
         textViewName = itemView.findViewById(R.id.passengerNameTicket);
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
