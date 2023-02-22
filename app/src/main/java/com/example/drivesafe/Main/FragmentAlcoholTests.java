package com.example.drivesafe.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drivesafe.Entities.AlcoholTest;
import com.example.drivesafe.MyTestsAdapter;
import com.example.drivesafe.R;
import com.example.drivesafe.Entities.Test;
import com.example.drivesafe.Entities.User;

import java.util.List;


public class FragmentAlcoholTests extends Fragment {
    private RecyclerView alcoholTests_RSV_view;
    View view;
    List<AlcoholTest> myTests;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                User user = intent.getParcelableExtra(MainActivity.USER);
                updateUI(user);
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alcohol_tests, container, false);
        User user = getArguments().getParcelable(MainActivity.USER);
        findViews(view, user);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                broadcastReceiver, new IntentFilter(MainActivity.UPDATE_UI)
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver);
    }

    private void findViews(View view, User user) {
        alcoholTests_RSV_view = view.findViewById(R.id.alcoholTests_RSV_view);
        updateUI(user);
    }

    private void updateUI(User user) {
        myTests = user.getUserTests();
        alcoholTests_RSV_view.setLayoutManager(new LinearLayoutManager(getContext()));
        alcoholTests_RSV_view.setAdapter(new MyTestsAdapter(getContext(), myTests));
    }
}