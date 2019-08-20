package com.king.mobile.android;

import com.king.mobile.base.BaseFragment;

public class SettingFragment extends BaseFragment {
    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.layout_setting;
    }
}
