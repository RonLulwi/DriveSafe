package com.example.drivesafe.Main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.drivesafe.App;
import com.example.drivesafe.DriveSafeController;
import com.example.drivesafe.Entities.BypassAttempts;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.MyBypassAdapter;
import com.example.drivesafe.R;
import java.util.List;

public class FragmentBypassAttempt extends Fragment {

    private RecyclerView bypass_RSV_view;
    View view;
    List<BypassAttempts> bypassAttempts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bypass_attempt, container, false);
        UserEntity user = (UserEntity) getArguments().getSerializable(MainActivity.USER);
        findViews(view, user);
        return view;
    }

    private void findViews(View view, UserEntity user) {
        bypass_RSV_view = view.findViewById(R.id.bypass_RSV_view);
        getBypassAttemptFromDB(user.getId());
    }

    private void getBypassAttemptFromDB(String userId) {
        new DriveSafeController().getBypassAttempts(userId, new DriveSafeController.CallBack_bypassAttempts() {
            @Override
            public void updateBypassAttempts(List<BypassAttempts> bypassAttempts) {
                if(bypassAttempts.isEmpty())
                    Toast.makeText(App.getAppContext(), "There is no Bypass attempts  to show.", Toast.LENGTH_SHORT).show();
                else
                    updateUI(bypassAttempts);
            }
        });
    }

    private void updateUI(List<BypassAttempts> bypassAttempts) {
        this.bypassAttempts = bypassAttempts;
        bypass_RSV_view.setLayoutManager(new LinearLayoutManager(getContext()));
        bypass_RSV_view.setAdapter(new MyBypassAdapter(getContext(), bypassAttempts));
    }
}