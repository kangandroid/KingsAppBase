package com.king.player.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.king.mobile.base.BaseFragment;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.adapter.RemoteVideoListAdapter;
import com.king.player.video.VideoInfo;
import com.king.player.view.PlayerActivity;
import com.king.player.viewmodel.VideoViewModel;

public class LatestFragment extends BaseFragment {
    private VideoViewModel videoViewModel;
    private RemoteVideoListAdapter adapter;
    private FragmentActivity activity;

    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected void initView(@NonNull View view) {

    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitleBarColorInt(Color.WHITE)
                .immersive(this, true)
                .setTitle("")
                .invalidate();

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
    }


    private void play(VideoInfo videoInfo) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra("videoInfo", videoInfo);
        startActivity(intent);
    }
}
