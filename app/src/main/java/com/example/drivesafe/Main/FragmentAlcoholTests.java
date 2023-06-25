package com.example.drivesafe.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.drivesafe.App;
import com.example.drivesafe.DriveSafeController;
import com.example.drivesafe.Entities.AlcoholTest;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.MyTestsAdapter;
import com.example.drivesafe.R;
import java.util.List;


public class FragmentAlcoholTests extends Fragment {
    private RecyclerView alcoholTests_RSV_view;
    View view;
    List<AlcoholTest> alcoholTests;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                UserEntity user = (UserEntity) intent.getSerializableExtra(MainActivity.USER);
                getAlcoholTestsFromDB(user.getId());
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alcohol_tests, container, false);
        UserEntity user = (UserEntity) getArguments().getSerializable(MainActivity.USER);
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

    private void findViews(View view, UserEntity user) {
        alcoholTests_RSV_view = view.findViewById(R.id.alcoholTests_RSV_view);
        getAlcoholTestsFromDB(user.getId());
    }

    private void getAlcoholTestsFromDB(String userId){
        new DriveSafeController().getAlcoholTests(userId, new DriveSafeController.CallBack_AlcoholTests() {
            @Override
            public void updateAlcoholTests(List<AlcoholTest> tests) {
                if(tests.isEmpty())
                    Toast.makeText(App.getAppContext(), "There is no Alcohol Tests to show.", Toast.LENGTH_SHORT).show();
                else
                    updateUI(tests);

            }
        });
    }

    private void updateUI(List<AlcoholTest> alcoholTests) {
        this.alcoholTests = alcoholTests;
        alcoholTests_RSV_view.setLayoutManager(new LinearLayoutManager(getContext()));
        alcoholTests_RSV_view.setAdapter(new MyTestsAdapter(getContext(), alcoholTests));
    }
}