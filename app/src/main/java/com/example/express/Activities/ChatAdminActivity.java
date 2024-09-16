package com.example.express.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Adapters.ChatAdapter;
import com.example.express.Classes.ChatModel;
import com.example.express.R;
import com.example.express.databinding.ActivityChatAdminBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class ChatAdminActivity extends AppCompatActivity {

    ActivityChatAdminBinding binding;

    ArrayList<ChatModel> chatModelArrayList = new ArrayList<>();
    ChatAdapter chatAdapter;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    Bitmap selectedImageBitmap;
    boolean scrollingToBottom = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Difficulty finding tickets for dates.", null, "0000", ChatModel.ViewTypeSent));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Difficulty finding tickets for dates.", null, "0000", ChatModel.ViewTypeSent));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Difficulty finding tickets for dates.", null, "0000", ChatModel.ViewTypeSent));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Difficulty finding tickets for dates.", null, "0000", ChatModel.ViewTypeSent));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Difficulty finding tickets for dates.", null, "0000", ChatModel.ViewTypeSent));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Difficulty finding tickets for dates.", null, "0000", ChatModel.ViewTypeSent));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "msg", null, "0000", ChatModel.ViewTypeReceived));
        chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, "Hi thanks for your massage. We'll get back to your within 24 hours.", null, "0000", ChatModel.ViewTypeReceived));



        chatAdapter = new ChatAdapter(chatModelArrayList, this);
        binding.ChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.ChatRecyclerView.setAdapter(chatAdapter);
        binding.ChatRecyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);

//        ImageSelectFragment imageSelectFragmentBottomSheet = new ImageSelectFragment( this,R.style.BottomSheetTheme);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatAdminActivity.this, R.style.BottomSheetTheme);
        View view = LayoutInflater.from(ChatAdminActivity.this).inflate(R.layout.botomsheet_test, findViewById(R.id.bottom_sheet));
        bottomSheetDialog.setContentView(view);


        binding.imgAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.show();
            }
        });


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            Uri selectedImageUri = data.getData();

                            try {
                                selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            LocalTime localTime = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                localTime = LocalTime.now();
                            }
                            int houre= 0;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                houre = localTime.getHour();
                            }
                            int min = 0;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                min = localTime.getMinute();
                            }
                            String time =houre+":"+min;
                            chatModelArrayList.add(new ChatModel(ChatModel.msgTypeImg, "msg", selectedImageUri, time+"ago ", ChatModel.ViewTypeReceived));
                            chatAdapter.notifyItemInserted(chatModelArrayList.size() - 1);
                            binding.ChatRecyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                            bottomSheetDialog.dismiss();


                        }
                    }
                });




        binding.imgMsgSent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = binding.EdtMsgType.getText().toString();

                LocalTime localTime = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    localTime = LocalTime.now();
                }
                int houre= 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    houre = localTime.getHour();
                }
                int min = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    min = localTime.getMinute();
                }
                String time =houre+":"+min;

                if (binding.EdtMsgType.getText().toString().isEmpty()){


                }
                else {

                    chatModelArrayList.add(new ChatModel(ChatModel.msgTypeTxt, msg, null, time+" ago", ChatModel.ViewTypeReceived));
                    chatAdapter.notifyItemInserted(chatModelArrayList.size() - 1);
                    binding.ChatRecyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                    binding.EdtMsgType.setText("");
                }



            }
        });



        ImageView imageView = view.findViewById(R.id.slImgGallery);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();

            }
        });

        Button btnCancel = view.findViewById(R.id.imgCancelBtn);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        ImageView imgphoto = view.findViewById(R.id.slImgPhotos);
        imgphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotosImgChooser();
            }
        });




        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                binding.getRoot().getWindowVisibleDisplayFrame(r);
                int screenHeight = binding.getRoot().getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;


                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.linearLayout.getLayoutParams();
                if (keypadHeight > screenHeight * 0.15) {

                    params.bottomMargin = keypadHeight;
                    if (!scrollingToBottom) {
                        scrollingToBottom = true;
                        binding.ChatRecyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);

                    }


                } else {
                    params.bottomMargin = 0;
                    scrollingToBottom=false;


                }
                binding.linearLayout.setLayoutParams(params);

            }
        });




        binding.ChatRecyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        LayoutInflater inflater1 = this.getLayoutInflater();
        View dialogView1 = inflater1.inflate(R.layout.dialog_close_complain, null);
        builder1.setView(dialogView1);
        AlertDialog dialog1 = builder1.create();
        dialog1.getWindow().setBackgroundDrawableResource(R.color.transparent);


        TextView complainClose = dialogView1.findViewById(R.id.dilComplainClose);
        complainClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("KeyValue", "someValue");
                setResult(Activity.RESULT_FIRST_USER, intent);
                finish();

            }
        });
        TextView complainCancle = dialogView1.findViewById(R.id.dilComplainCancel);
        complainCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.close_ticket_dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);


        TextView back = dialogView.findViewById(R.id.dilBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView closeTicket = dialogView.findViewById(R.id.dilCloseTicktBtn);
        closeTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
                dialog.dismiss();
            }
        });
        TextView cancelDil = dialogView.findViewById(R.id.dilcancelBtn);
        cancelDil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

            }
        });


    }



    private void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(intent);


    }

    private void PhotosImgChooser() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncher.launch(intent);


    }

}


