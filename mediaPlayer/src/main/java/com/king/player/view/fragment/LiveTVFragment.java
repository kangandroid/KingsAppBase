package com.king.player.view.fragment;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.base.BaseListFragment;
import com.king.player.adapter.LiveTVListAdapter;
import com.king.player.video.VideoInfo;
import com.king.player.view.PlayerActivity;
import com.king.player.viewmodel.VideoViewModel;

public class LiveTVFragment extends BaseListFragment {
    private VideoViewModel videoViewModel;
    private LiveTVListAdapter adapter;
    private FragmentActivity activity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
    }

    @Override
    protected void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));

    }

    @Override
    protected BaseListAdapter createAdapter() {

        adapter = new LiveTVListAdapter(activity);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((videoInfo, view, position) -> {
            play(videoInfo);
//            new ScreenCastFragment(videoInfo).cast(getFragmentManager());
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
