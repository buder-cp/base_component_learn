package com.example.lib_multi_ly;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lib_multi_ly.model.DividerModel;
import com.example.lib_multi_ly.model.UserBaseModel;
import com.example.lib_multi_ly.model.UserMultiModel;
import com.example.lib_multi_ly.model.UserSingleModel;

import java.util.ArrayList;

public class MultiLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainUserAdapter mAdapter;
    private ArrayList<UserBaseModel> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mullti_main);

        initView();
        loadData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.multi_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainUserAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {

        mItems.add(new UserSingleModel());
        mItems.add(new UserMultiModel());
        mItems.add(new DividerModel());
        mItems.add(new UserSingleModel());
        mItems.add(new UserMultiModel());
        mItems.add(new DividerModel());
        mItems.add(new UserSingleModel());
        mItems.add(new UserMultiModel());
        mItems.add(new DividerModel());
        mItems.add(new UserSingleModel());
        mItems.add(new UserMultiModel());
        mItems.add(new DividerModel());
        mItems.add(new UserSingleModel());
        mItems.add(new UserMultiModel());
        mItems.add(new DividerModel());
        mItems.add(new UserSingleModel());
        mItems.add(new UserMultiModel());
        mItems.add(new DividerModel());

        //获取数据设置
        mAdapter.setDatas(mItems);
    }
}
