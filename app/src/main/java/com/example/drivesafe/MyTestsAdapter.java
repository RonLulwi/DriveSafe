package com.example.drivesafe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drivesafe.Entities.AlcoholTest;
import com.example.drivesafe.Entities.AlcoholTestResult;
import java.text.SimpleDateFormat;
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
        String result = tests.get(position).getResult().toString();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        String date = formatter.format(tests.get(position).getTestTimestamp());
        if(tests.get(position).getResult().equals(AlcoholTestResult.FAILED))
            holder.recyclerView_IMG_status.setImageResource(R.drawable.ic_failed);
        else
            holder.recyclerView_IMG_status.setImageResource(R.drawable.ic_passed);
        holder.recyclerView_LBL_status.setText(result);
        holder.recyclerView_LBL_date.setText(date);
    }

    @Override
    public int getItemCount() {
        if (tests ==null)
            return 0;
        return tests.size();
    }
}
