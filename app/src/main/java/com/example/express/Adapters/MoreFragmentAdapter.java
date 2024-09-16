package com.example.express.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.MoreModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class MoreFragmentAdapter extends RecyclerView.Adapter<MoreFragmentAdapter.ViewHolder>{

    ArrayList<MoreModel> moreModelArrayList;
    ItemClickListener itemClickListener;

    public MoreFragmentAdapter(ArrayList<MoreModel> moreModelArrayList, ItemClickListener itemClickListener) {
        this.moreModelArrayList = moreModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.more_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(moreModelArrayList.get(position).getText());
        holder.imageView.setImageResource(moreModelArrayList.get(position).getImageView());
        holder.bind(position,itemClickListener);

    }

    @Override
    public int getItemCount() {
        return moreModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.titleMoreElement);
            imageView = itemView.findViewById(R.id.iconMoreElement);
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
