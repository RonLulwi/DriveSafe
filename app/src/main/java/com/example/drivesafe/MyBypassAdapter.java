package com.example.drivesafe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drivesafe.Entities.BypassAttempts;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyBypassAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<BypassAttempts> bypassAttempts;

    public MyBypassAdapter(Context context, List<BypassAttempts> bypassAttempts) {
        this.context = context;
        this.bypassAttempts = bypassAttempts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_bypass_attempt, parent, false));    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        String date = formatter.format(bypassAttempts.get(position).getTimestamp());
        holder.recyclerView_LBL_info.setText("Bypass attempt was detected at " + date);
        String performerString = bypassAttempts.get(position).getTestPerformer();
        String driverString = bypassAttempts.get(position).getDriver();


        byte[] decodedPerformer = android.util.Base64.decode(performerString, android.util.Base64.DEFAULT);
        byte[] decodedDriver = android.util.Base64.decode(driverString, android.util.Base64.DEFAULT);

        Bitmap bitmapPerformer = BitmapFactory.decodeByteArray(decodedPerformer, 0, decodedPerformer.length);
        Bitmap bitmapDriver = BitmapFactory.decodeByteArray(decodedDriver, 0, decodedDriver.length);

        holder.recyclerView_IMG_performer.setImageBitmap(bitmapPerformer);
        holder.recyclerView_IMG_driver.setImageBitmap(bitmapDriver);
    }
    @Override
    public int getItemCount() {
        if (bypassAttempts ==null)
            return 0;
        return bypassAttempts.size();
    }
}
