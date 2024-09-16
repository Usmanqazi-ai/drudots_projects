package com.example.express.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.HelpModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ViewHolder> {
    ArrayList<HelpModel> helpModelArrayList;
    ItemClickListener itemClickListener;

    public HelpAdapter(ArrayList<HelpModel> helpModelArrayList, ItemClickListener itemClickListener) {
        this.helpModelArrayList = helpModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.help_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(helpModelArrayList.get(position).getImg());
        holder.textView.setText(helpModelArrayList.get(position).getTitle());
        holder.bind(position,itemClickListener);

    }

    @Override
    public int getItemCount() {
        return helpModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.helpListTxtView);
            imageView = itemView.findViewById(R.id.helpListImg);
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
