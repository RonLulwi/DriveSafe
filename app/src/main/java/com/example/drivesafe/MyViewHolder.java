package com.example.drivesafe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView recyclerView_IMG_baseline, recyclerView_IMG_status;
    TextView recyclerView_LBL_time, recyclerView_LBL_date, recyclerView_LBL_status;
    MaterialTextView recyclerView_LBL_devider,  recyclerView_LBL_from, recyclerView_LBL_until, recyclerView_LBL_textDate;
    SwitchCompat recyclerView_RBTN_onof;

    public MyViewHolder(@NonNull View view) {
        super(view);

        // recycler of tests
        recyclerView_IMG_baseline = view.findViewById(R.id.recyclerView_IMG_baseline);
        recyclerView_IMG_status = view.findViewById(R.id.recyclerView_IMG_status);
        recyclerView_LBL_time = view.findViewById(R.id.recyclerView_LBL_time);
        recyclerView_LBL_date = view.findViewById(R.id.recyclerView_LBL_date);
        recyclerView_LBL_status = view.findViewById(R.id.recyclerView_LBL_status);

        // recycler of scheduler
        recyclerView_LBL_devider = view.findViewById(R.id.recyclerView_LBL_devider);
        recyclerView_LBL_from = view.findViewById(R.id.recyclerView_LBL_from);
        recyclerView_LBL_until = view.findViewById(R.id.recyclerView_LBL_until);
        recyclerView_LBL_textDate = view.findViewById(R.id.recyclerView_LBL_textDate);
        recyclerView_RBTN_onof = view.findViewById(R.id.recyclerView_RBTN_onof);
    }
}