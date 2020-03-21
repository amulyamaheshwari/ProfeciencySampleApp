package com.amulya.test.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amulya.test.R;
import com.amulya.test.adapter.RecyclerViewAdapter;
import com.amulya.test.pojo.RootDataModel;
import com.amulya.test.viewmodel.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ContentViewModel model = new ViewModelProvider(this).get(ContentViewModel.class);

        model.getData().observe(this, (RootDataModel dataModel) -> {
            getSupportActionBar().setTitle(dataModel.getTitle());
            adapter = new RecyclerViewAdapter(MainActivity.this, dataModel.getRows());
            recyclerView.setAdapter(adapter);
        });
    }
}