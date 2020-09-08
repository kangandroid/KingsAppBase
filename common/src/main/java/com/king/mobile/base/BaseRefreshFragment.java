package com.king.mobile.base;

import android.view.View;

import androidx.annotation.NonNull;

import com.king.refresh.normalstyle.NestRefreshLayout;

public abstract class BaseRefreshFragment extends BaseFragment {
    @Override
    protected void initView(@NonNull View view) {
        NestRefreshLayout refreshLayout = new NestRefreshLayout(getActivity());
        mContainer.addView(refreshLayout);
    }
}
