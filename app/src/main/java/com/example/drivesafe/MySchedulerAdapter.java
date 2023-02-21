package com.example.drivesafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivesafe.Entities.Dates;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.recyclerView_RBTN_onof.setChecked(dates.get(position).getActive());
        holder.recyclerView_RBTN_onof.setOnClickListener(v -> {
            if(dates.get(position).getActive())
                dates.get(position).setActive(false);
            else
                dates.get(position).setActive(true);
            notifyFB();
        });
        holder.view.setOnLongClickListener(v -> {
            Toast.makeText(context, "Activation date has been deleted!", Toast.LENGTH_SHORT).show();
            dates.remove(dates.get(position));
            notifyFB();
            return true;
        });
    }

    private void notifyFB(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase.child("users").child(fbUser.getUid()).child("userActivationDates").setValue(this.dates);
    }
    @Override
    public int getItemCount() {
        if (dates ==null)
            return 0;
        return dates.size();
    }
}


