package com.example.express.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Classes.FAQModel;
import com.example.express.R;

import java.util.ArrayList;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

    ArrayList<FAQModel> faqModelArrayList = new ArrayList<>();
        Context context;



    public FAQAdapter(ArrayList<FAQModel> faqModelArrayList, Context context) {
        this.faqModelArrayList = faqModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.quesTxt.setText(faqModelArrayList.get(position).getQus());
        holder.ansTxt.setText(faqModelArrayList.get(position).getAns());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isShow = faqModelArrayList.get(position).isShow();
                if (isShow){
                    holder.imageView.setRotation(360f);
                    holder.ansTxt.setVisibility(View.GONE);

                    faqModelArrayList.get(position).setShow(false);
                }else {
                    holder.imageView.setRotation(90f);
                    holder.ansTxt.setVisibility(View.VISIBLE);

                    faqModelArrayList.get(position).setShow(true);
                }
            }
        });


            }


    @Override
    public int getItemCount() {
        return faqModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView quesTxt;
        TextView ansTxt;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quesTxt = itemView.findViewById(R.id.txtQues);
            ansTxt=itemView.findViewById(R.id.txtAns);
            imageView=itemView.findViewById(R.id.imgArrow);
        }
    }
}
