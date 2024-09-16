package com.example.express.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Activities.AboutUsActivity;
import com.example.express.Activities.ChangePasswordActivity;
import com.example.express.Activities.HelpActivity;
import com.example.express.Activities.ProfileActivity;
import com.example.express.Activities.SignInActivity;
import com.example.express.Adapters.MoreFragmentAdapter;
import com.example.express.Classes.MoreModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;
import com.example.express.databinding.FragmentMoreBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MoreFragment extends Fragment {


        FragmentMoreBinding binding;
        ArrayList<MoreModel> moreModelArrayList = new ArrayList<>();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_dialog, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        TextView okBtn = dialogView.findViewById(R.id.txtOk);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity().getApplication(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        TextView cancelBtn = dialogView.findViewById(R.id.txtCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        binding.moreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MoreFragmentAdapter moreFragmentAdapter = new MoreFragmentAdapter(moreModelArrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position ==0){
                    Intent intent = new Intent(getActivity().getApplication(), ChangePasswordActivity.class);
                    startActivity(intent);
                }
                if (position ==1){
                    Intent intent = new Intent(getActivity().getApplication(), ProfileActivity.class);
                    startActivity(intent);
                }
                if (position ==2){
                    Intent intent = new Intent(getActivity().getApplication(), HelpActivity.class);
                    startActivity(intent);
                }
                if (position==3){
                    Intent intent = new Intent(getActivity().getApplication(), AboutUsActivity.class);
                    startActivity(intent);
                }if(position == 4){
                    dialog.show();

                }


            }
        });
        binding.moreRecyclerView.setAdapter(moreFragmentAdapter);

        moreModelArrayList.add(new MoreModel(R.drawable.ic_key,"Change Password"));
        moreModelArrayList.add(new MoreModel(R.drawable.person,"Profile"));
        moreModelArrayList.add(new MoreModel(R.drawable.ic_help,"Help"));
        moreModelArrayList.add(new MoreModel(R.drawable.ic_about_us,"About Us"));
        moreModelArrayList.add(new MoreModel(R.drawable.ic_logout,"Log Out"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}