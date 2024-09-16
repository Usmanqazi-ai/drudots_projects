package com.example.express.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.SeatSelectionModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;

import java.util.ArrayList;

public class Seat42SelectionAdapter extends RecyclerView.Adapter<com.example.express.Adapters.SeatSelectionAdapter.ViewHolder> {

    ArrayList<SeatSelectionModel> seatSelectionModelArrayList;
    ItemClickListener itemClickListener;
    int numColumns;
    private int selectedPosition = -1;

    public Seat42SelectionAdapter(ArrayList<SeatSelectionModel> seatSelectionModelArrayList, ItemClickListener itemClickListener, int numColumns) {
        this.seatSelectionModelArrayList = seatSelectionModelArrayList;
        this.itemClickListener = itemClickListener;
        this.numColumns = numColumns;

    }


    @NonNull
    @Override
    public com.example.express.Adapters.SeatSelectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        com.example.express.Adapters.SeatSelectionAdapter.ViewHolder viewHolder = new com.example.express.Adapters.SeatSelectionAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_42_seat_selection, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.express.Adapters.SeatSelectionAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SeatSelectionModel seatSelectionModel = seatSelectionModelArrayList.get(position);
        int column = position % numColumns;
        boolean isLastInThirdColumn = (column == 2 && position + numColumns >= seatSelectionModelArrayList.size()) ||
                (column == 2 && (position / numColumns + 1) * numColumns > seatSelectionModelArrayList.size());


        if ((column == 2)) {
            holder.itemView.setVisibility(isLastInThirdColumn ? View.VISIBLE : View.INVISIBLE);
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
        }

        if (holder.itemView.getVisibility() == View.VISIBLE) {
            int displayNumber = calculateDisplayNumber(position);
            holder.seatNum.setText("" + displayNumber);
        }

        if (seatSelectionModel.isSelected()) {
            holder.seatNum.setBackgroundResource(R.drawable.seleted_bg_seat);
        } else {
            holder.seatNum.setBackgroundResource(R.drawable.bg_seat);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int displayNumber = calculateDisplayNumber(position);

                if (holder.itemView.getVisibility() == View.VISIBLE) {
                    itemClickListener.onItemClick(displayNumber);
                }

                if (itemClickListener != null) {

                    if (selectedPosition >= 0 && selectedPosition != position) {
                        seatSelectionModelArrayList.get(selectedPosition).setSelected(false);
                        notifyItemChanged(selectedPosition);
                    }
                }
                seatSelectionModel.setSelected(true);
                selectedPosition = position;
                notifyItemChanged(position);


            }
        });


    }

    @Override
    public int getItemCount() {
        return seatSelectionModelArrayList.size();
    }

    private int calculateDisplayNumber(int position) {
        int count = 1;
        for (int i = 0; i < position; i++) {
            int column = i % numColumns;
            if (column != 2 || (column == 2 && (i + numColumns) >= seatSelectionModelArrayList.size() || (i / numColumns + 1) * numColumns > seatSelectionModelArrayList.size())) {
                count++;
            }
        }
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView seatNum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNum = itemView.findViewById(R.id.seatNum);


        }

    }
}


