package com.king.mobile.android;

import android.view.View;

import androidx.annotation.NonNull;

import com.king.mobile.base.BaseFragment;

public class StubFragment extends BaseFragment {
    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }

    @Override
    protected void initView(@NonNull View view) {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.layout_setting;
    }
}
