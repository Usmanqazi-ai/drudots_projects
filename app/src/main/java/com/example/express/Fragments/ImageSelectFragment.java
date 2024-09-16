package com.example.express.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.express.databinding.FragmentImageSelectBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;


public class  ImageSelectFragment extends BottomSheetDialogFragment {

    private static final int SELECT_PICTURE = 200;
    FragmentImageSelectBinding binding;
   private ActivityResultLauncher<Intent> activityResultLauncher;
    private final OnImageSelectedListener listener;
    BottomSheetBehavior bottomSheetBehavior;

    public ImageSelectFragment(OnImageSelectedListener listener, int bottomSheetTheme) {
        this.listener= listener;


    }

    public interface OnImageSelectedListener {
        void onImageSelected(Bitmap imgBitmap);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);








        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            Uri selectedImageUri = data.getData();
                            Bitmap selectedImageBitmap = null;
                            try {
                                selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), selectedImageUri);


                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            listener.onImageSelected(selectedImageBitmap);

                            dismiss();



                        }
                    }
                });




        binding.imgCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


            Resources resources ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentImageSelectBinding.inflate(getLayoutInflater());

        return binding.getRoot();


    }
    @Override
    public void dismiss() {
        super.dismiss();
    }




    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
    }
}