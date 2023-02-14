package com.example.drivesafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MySchedulerAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<Dates> dates;

    public MySchedulerAdapter(Context context, List<Dates> dates) {
        this.context = context;
        this.dates = dates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_scheduler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recyclerView_LBL_devider.setText(" - ");
        holder.recyclerView_LBL_from.setText(dates.get(position).getFrom());
        holder.recyclerView_LBL_until.setText(dates.get(position).getUntil());
        holder.recyclerView_LBL_textDate.setText(dates.get(position).getDate());
        holder.recyclerView_RBTN_onof.setChecked(dates.get(position).isActive());
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}


