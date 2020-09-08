package com.king.mobile.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.king.mobile.util.R;
import com.king.mobile.widget.TitleBar;

public abstract class BaseFragment extends Fragment {

    protected TitleBar titleBar;
    protected FrameLayout mContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if (isOverlay()) {
            view = inflater.inflate(R.layout.activity_base_overlay, null);
        } else {
            view = inflater.inflate(R.layout.activity_base, null);
        }
        titleBar = view.findViewById(R.id.title_bar);
        mContainer = view.findViewById(R.id.container);
        if (hasTitle()) {
            view.setVisibility(View.VISIBLE);
            setTitle(titleBar);
        } else {
            titleBar.setVisibility(View.GONE);
        }
        inflater.inflate(getContentLayoutId(), mContainer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(@NonNull View view);

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
}
