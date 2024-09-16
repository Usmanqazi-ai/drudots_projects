package com.example.express.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.BusResultModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class BusResultAdapter extends RecyclerView.Adapter<BusResultAdapter.ViewHolder> {

    static ArrayList<BusResultModel> list = new ArrayList<>();
    ItemClickListener itemClickListener;

    public BusResultAdapter(ArrayList<BusResultModel> list, ItemClickListener itemClickListener) {

        this.list = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.busresults_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewBusName.setText((CharSequence) list.get(position).getBusCompany());
        holder.timeDeparture.setText((CharSequence) list.get(position).getTimeDeapature());
        holder.bind(position, itemClickListener);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewBusName;
        TextView timeDeparture;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBusName = itemView.findViewById(R.id.busName);
            timeDeparture = itemView.findViewById(R.id.timeDeparture);
            linearLayout = itemView.findViewById(R.id.main);

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
