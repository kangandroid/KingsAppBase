package com.king.mobile.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.king.mobile.util.R;
import com.king.mobile.widget.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public abstract class BaseFragment extends Fragment {

    private TitleBar titleBar;
    protected SmartRefreshLayout mContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if (hasTitle()) {
            if (isOverlay()) {
                view = inflater.inflate(R.layout.activity_base_overlay, null);
            } else {
                view = inflater.inflate(R.layout.activity_base, null);
            }
            titleBar = view.findViewById(R.id.title_bar);
            setTitle(titleBar);
            mContainer = view.findViewById(R.id.container);
        } else {
            mContainer = new SmartRefreshLayout(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mContainer.setLayoutParams(params);
            view = mContainer;
        }
        inflater.inflate(getContentLayoutId(), mContainer);
        return view;
    }

    protected void setTitle(TitleBar titleBar) {
    }

    protected abstract @LayoutRes
    int getContentLayoutId();


    protected boolean isOverlay() {
        return false;
    }

    protected boolean hasTitle() {
        return false;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
