package com.example.drivesafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivesafe.Entities.AlcoholTest;
import com.example.drivesafe.Entities.Test;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyTestsAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<AlcoholTest> tests;

    public MyTestsAdapter(Context context, List<AlcoholTest> tests) {
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
        if(tests.get(position).getResult()){
            holder.recyclerView_LBL_status.setText("PASSED");
            holder.recyclerView_LBL_date.setText(tests.get(position).getDate());
            holder.recyclerView_IMG_status.setImageResource(R.drawable.ic_passed);
        }else{
            holder.recyclerView_LBL_status.setText("FAILED");
            holder.recyclerView_LBL_date.setText(tests.get(position).getDate());
            holder.recyclerView_IMG_status.setImageResource(R.drawable.ic_failed);
        }

        holder.view.setOnLongClickListener(v -> {
            Toast.makeText(context, "Activation date has been deleted!", Toast.LENGTH_SHORT).show();
            tests.remove(tests.get(position));
            notifyFB();
            return true;
        });
    }

    private void notifyFB(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase.child("users").child(fbUser.getUid()).child("userTests").setValue(this.tests);
    }

    @Override
    public int getItemCount() {
        if (tests ==null)
            return 0;
        return tests.size();
    }
}
