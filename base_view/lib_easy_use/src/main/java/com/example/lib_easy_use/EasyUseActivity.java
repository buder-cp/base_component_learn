package com.example.lib_easy_use;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class EasyUseActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RvAdapter mRvAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_main);

        initView();
        loadData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        ArrayList<UserModel> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add(new UserModel("name" + i, i + "."));
        }
        mRvAdapter = new RvAdapter();
        mRecyclerView.setAdapter(mRvAdapter);
        mRvAdapter.setData(arrayList);
    }

}
