package com.king.mobile.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.util.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public abstract class BaseListFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.layout_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        mContainer.setEnableLoadMore(false);
        mContainer.setEnableRefresh(false);
        mContainer.setEnableOverScrollDrag(true);
        adaptList(recyclerView);
    }

    protected abstract void adaptList(RecyclerView recyclerView);

}
