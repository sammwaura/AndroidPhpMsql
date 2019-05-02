package com.meshsami27.android_phpmysql.ui.ui.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.meshsami27.android_phpmysql.R;
import com.meshsami27.android_phpmysql.ui.ui.Insert.InsertActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recyclerview);

        floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.add:
                startActivity(new Intent(this, InsertActivity.class));
        }
    }
}

