package com.king.player.view.fragment;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseListFragment;
import com.king.player.LocalVideoListAdapter;
import com.king.player.model.VideoInfo;
import com.king.player.view.PlayerActivity;
import com.king.player.viewmodel.VideoViewModel;

import java.util.Objects;

import static android.widget.GridLayout.HORIZONTAL;
import static android.widget.GridLayout.VERTICAL;

public class RemoteVideoFragment extends BaseListFragment {
    private VideoViewModel videoViewModel;
    private LocalVideoListAdapter adapter;
    private FragmentActivity activity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
    }

    @Override
    protected void adaptList(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        adapter = new LocalVideoListAdapter(activity);
        Context context = Objects.requireNonNull(getContext());
        DividerItemDecoration decorV = new DividerItemDecoration(context, VERTICAL);
        DividerItemDecoration decorH = new DividerItemDecoration(context, HORIZONTAL);
        recyclerView.addItemDecoration(decorV);
        recyclerView.addItemDecoration(decorH);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLitener((videoInfo, view, position) -> play(videoInfo));
        videoViewModel.getRemoteVideoList().observe(this, list -> adapter.setData(list));
    }

    private void play(VideoInfo videoInfo) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra("videoInfo", videoInfo);
        startActivity(intent);
    }
}
