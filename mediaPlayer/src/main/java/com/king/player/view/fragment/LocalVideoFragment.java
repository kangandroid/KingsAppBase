package com.king.player.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.base.BaseListFragment;
import com.king.mobile.widget.TitleBar;
import com.king.player.adapter.LocalVideoListAdapter;
import com.king.player.video.VideoInfo;
import com.king.player.view.PlayerActivity;
import com.king.player.viewmodel.VideoViewModel;

import java.util.Objects;

import static android.widget.GridLayout.HORIZONTAL;
import static android.widget.GridLayout.VERTICAL;

public class LocalVideoFragment extends BaseListFragment {
    private VideoViewModel videoViewModel;
    private LocalVideoListAdapter adapter;
    private FragmentActivity activity;

    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitle("")
                .setTitleBarColorInt(Color.WHITE)
                .immersive(this, true)
                .invalidate();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
    }

    @Override
    protected void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        Context context = Objects.requireNonNull(getContext());
        DividerItemDecoration decorV = new DividerItemDecoration(context, VERTICAL);
        DividerItemDecoration decorH = new DividerItemDecoration(context, HORIZONTAL);
        recyclerView.addItemDecoration(decorV);
        recyclerView.addItemDecoration(decorH);
    }

    @Override
    protected BaseListAdapter createAdapter() {
        adapter = new LocalVideoListAdapter(activity);
        adapter.setOnItemClickListener((videoInfo, view, position) -> play(videoInfo));
        videoViewModel.getLocalVideoList().observe(this, list -> {
            adapter.setData(list);
        });
        return adapter;
    }

    @Override
    protected void requestData(int requestType) {

    }

    private void play(VideoInfo videoInfo) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra("videoInfo", videoInfo);
        startActivity(intent);
    }
}
