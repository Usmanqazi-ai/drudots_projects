package com.example.express.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.CountryModel;
import com.example.express.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    ArrayList<CountryModel> adapterArrayList;
    ListenerOnClick listenerOnClick;

    public interface ListenerOnClick {

        void onClickListener(CountryModel countryModel);
    }

    public void setListenerOnClick(ListenerOnClick listenerOnClick) {
        this.listenerOnClick = listenerOnClick;
    }

    public CountryAdapter(ArrayList<CountryModel> adapterArrayList) {
        this.adapterArrayList = adapterArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CountryModel countryModel = adapterArrayList.get(position);

        holder.countryName.setText(countryModel.getName());
        holder.countryCode.setText(countryModel.getDial_code());
        holder.imageView.setText(countryModel.getFlag());
        holder.itemView.setOnClickListener(v -> {
            if (listenerOnClick != null) {
                listenerOnClick.onClickListener(countryModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        return adapterArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView imageView;
        TextView countryName;
        TextView countryCode;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_flag);
            countryName = itemView.findViewById(R.id.countryName);
            countryCode = itemView.findViewById(R.id.countryCode);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
