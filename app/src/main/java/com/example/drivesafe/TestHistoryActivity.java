package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class TestHistoryActivity extends MenuForAllActivities {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_history);
        findViews();
        initViews();

        recyclerView = findViewById(R.id.history_RSV_view);

        List<Test> myTests = new ArrayList<>();

        myTests.add(new Test(R.drawable.ic_passed,  "PASSED",  "22:00",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_passed,  "PASSED",  "22:00",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_failed,  "FAILED",  "2:00",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_failed,  "FAILED",  "21:00",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_failed,  "FAILED",  "21:22",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_passed,  "PASSED",  "24:00",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_failed,  "FAILED",  "2:01",  "01/06/09"));
        myTests.add(new Test(R.drawable.ic_passed,  "PASSED",  "2:02",  "01/06/09"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyTestsAdapter(getApplicationContext(), myTests));

    }



    private void findViews() {
        toolbar = findViewById(R.id.appbar);
    }
    private void initViews() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> showPopupMenu(view, R.id.popup_menu_alcoholTests));
        toolbar.setTitle("HISTORY");
    }
}