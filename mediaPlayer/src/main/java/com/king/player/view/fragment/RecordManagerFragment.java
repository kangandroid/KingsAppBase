package com.king.player.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseFragment;
import com.king.player.R;
import com.king.player.adapter.RecentVideoListAdapter;
import com.king.player.view.widget.ItemActionView;

import static android.widget.GridLayout.VERTICAL;

public class RecordManagerFragment extends BaseFragment {


    private ItemActionView avRecent;
    private RecyclerView recentList;
    private RecentVideoListAdapter adapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(@NonNull View view) {
        avRecent = view.findViewById(R.id.av_recent);
        recentList = view.findViewById(R.id.recent_list);
        recentList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        DividerItemDecoration decorV = new DividerItemDecoration(getContext(), VERTICAL);
        recentList.addItemDecoration(decorV);
        adapter = new RecentVideoListAdapter(getContext());
        recentList.setAdapter(adapter);
        avRecent.setOnClickListener(v -> {
        });
    }
}


