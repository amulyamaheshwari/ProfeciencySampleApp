package com.amulya.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amulya.test.R;
import com.amulya.test.adapter.RecyclerViewAdapter;
import com.amulya.test.networkmanager.CheckConnection;
import com.amulya.test.pojo.RootDataModel;
import com.amulya.test.viewmodel.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");
        setContentView(R.layout.activity_main);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchTimelineAsync();

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                fetchTimelineAsync();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeContainer.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void fetchTimelineAsync() {
        ContentViewModel model = new ViewModelProvider(this).get(ContentViewModel.class);
        if (CheckConnection.isConnection(MainActivity.this)) {
            model.getData().observe(this, (RootDataModel dataModel) -> {
                getSupportActionBar().setTitle(dataModel.getTitle());
                adapter = new RecyclerViewAdapter(MainActivity.this, dataModel.getRows());
                recyclerView.setAdapter(adapter);
            });
        } else {
            getSupportActionBar().setTitle("No network connection!");
            Toast.makeText(this, "Network is not available!", Toast.LENGTH_SHORT).show();
        }
    }
}
