package com.king.player;

import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseActivity;

public class VideoListActivity extends BaseActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.layout_recycle_list;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
    }
}
