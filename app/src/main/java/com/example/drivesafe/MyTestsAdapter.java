package com.example.drivesafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivesafe.Entities.Test;

import java.util.List;

public class MyTestsAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Test> tests;

    public MyTestsAdapter(Context context, List<Test> tests) {
        this.context = context;
        this.tests = tests;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_alcohol_test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recyclerView_LBL_status.setText(tests.get(position).getInfo());
        holder.recyclerView_LBL_date.setText(tests.get(position).getDate());
        holder.recyclerView_LBL_time.setText(tests.get(position).getTime());
        holder.recyclerView_IMG_status.setImageResource(tests.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }
}
