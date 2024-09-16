package com.example.express.Adapters;

import static com.example.express.Classes.ChatModel.ViewTypeReceived;
import static com.example.express.Classes.ChatModel.ViewTypeSent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Activities.ImageViewActivity;
import com.example.express.Classes.ChatModel;
import com.example.express.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ChatModel> chatModelArrayList ;
    public static final int msgTypeImg = 0;
    public static final int msgTypeTxt = 1;
    Uri imgRec;

    Context context;
    public ChatAdapter(ArrayList<ChatModel> chatModelArrayList,Context context ) {
        this.context=context;

        this.chatModelArrayList = chatModelArrayList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatModelArrayList.get(position).getViewType()) {
            case 0:
                return ViewTypeSent;
            case 1:
                return ViewTypeReceived;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewTypeSent:
                View sent = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_sent, parent, false);
                return new ViewHolderMsgSent(sent);
            case ViewTypeReceived:
                View received = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_message, parent, false);
                return new ViewHolderMsgReceived(received);
            default:
                return null ;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if (getItemViewType(position) == 0) {

            if (chatModelArrayList.get(position).getMsgType() == msgTypeTxt) {

                ((ViewHolderMsgSent) holder).imageSent.setVisibility(View.GONE);
                ((ViewHolderMsgSent) holder).msgSent.setVisibility(View.VISIBLE);

                String txtSentMsg = chatModelArrayList.get(position).getSentMsg();
                ((ViewHolderMsgSent) holder).msgSent.setText(txtSentMsg);
                String txtSentMsgTime = chatModelArrayList.get(position).getTime();
                ((ViewHolderMsgSent) holder).timeMsgSent.setText(txtSentMsgTime);
                Uri imgSent = chatModelArrayList.get(position).getSentImage();
                ((ViewHolderMsgSent) holder).imageSent.setImageURI(imgSent);
            } else if (chatModelArrayList.get(position).getMsgType() == msgTypeImg) {
                ((ViewHolderMsgSent) holder).imageSent.setVisibility(View.VISIBLE);
                ((ViewHolderMsgSent) holder).msgSent.setVisibility(View.GONE);

                String txtSentMsg = chatModelArrayList.get(position).getSentMsg();
                ((ViewHolderMsgSent) holder).msgSent.setText(txtSentMsg);
                String txtSentMsgTime = chatModelArrayList.get(position).getTime();
                ((ViewHolderMsgSent) holder).timeMsgSent.setText(txtSentMsgTime);
                Uri imgSent = chatModelArrayList.get(position).getSentImage();
                ((ViewHolderMsgSent) holder).imageSent.setImageURI(imgSent);
            }

        } else if (getItemViewType(position) == 1){
            if (chatModelArrayList.get(position).getMsgType() == msgTypeTxt) {
                ((ViewHolderMsgReceived) holder).imageReceived.setVisibility(View.GONE);
                ((ViewHolderMsgReceived) holder).msgReceived.setVisibility(View.VISIBLE);

                String txtReceivedMsg = chatModelArrayList.get(position).getSentMsg();
                ((ViewHolderMsgReceived) holder).msgReceived.setText(txtReceivedMsg);
                String txtRecMsgTime = chatModelArrayList.get(position).getTime();
                ((ViewHolderMsgReceived) holder).timeMsgReceived.setText(txtRecMsgTime);
                imgRec = chatModelArrayList.get(position).getSentImage();
                ((ViewHolderMsgReceived) holder).imageReceived.setImageURI(imgRec);
            } else if (chatModelArrayList.get(position).getMsgType() == msgTypeImg) {
                ((ViewHolderMsgReceived) holder).imageReceived.setVisibility(View.VISIBLE);
                ((ViewHolderMsgReceived) holder).msgReceived.setVisibility(View.GONE);

                String txtReceivedMsg = chatModelArrayList.get(position).getSentMsg();
                ((ViewHolderMsgReceived) holder).msgReceived.setText(txtReceivedMsg);
                String txtRecMsgTime = chatModelArrayList.get(position).getTime();
                ((ViewHolderMsgReceived) holder).timeMsgReceived.setText(txtRecMsgTime);
                imgRec  = chatModelArrayList.get(position).getSentImage();
                ((ViewHolderMsgReceived) holder).imageReceived.setImageURI(imgRec);
            }
        }

                if (chatModelArrayList.get(position).getMsgType() ==msgTypeImg){
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imgRec=chatModelArrayList.get(position).getSentImage();
                            Uri uri = Uri.parse(String.valueOf(imgRec));
                            Intent intent = new Intent(context, ImageViewActivity.class);
                            intent.putExtra("imgValue",uri.toString());
                            context.startActivity(intent);

                        }
                    });

                }



    }

    @Override
    public int getItemCount() {
        return chatModelArrayList.size();
    }

    public static class ViewHolderMsgReceived extends RecyclerView.ViewHolder {

            TextView msgReceived;
            TextView timeMsgReceived;
            ImageView imageReceived;
        public ViewHolderMsgReceived(@NonNull View itemView) {
            super(itemView);
            msgReceived = itemView.findViewById(R.id.txtMsgReceived);
            timeMsgReceived = itemView.findViewById(R.id.timeMsgReceived);
            imageReceived = itemView.findViewById(R.id.imgMsgReceived);
        }
    }

    public static  class ViewHolderMsgSent extends RecyclerView.ViewHolder {

        TextView msgSent;
        TextView timeMsgSent;
        ImageView imageSent;
        public ViewHolderMsgSent(@NonNull View itemView) {
            super(itemView);

            msgSent = itemView.findViewById(R.id.txtMsgSent);
            timeMsgSent = itemView.findViewById(R.id.txtMsgTimeSent);
            imageSent = itemView.findViewById(R.id.imgMsgSent);
        }
    }
}
