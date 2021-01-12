package com.king.mobile.testapp.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.king.mobile.testapp.R;

class MyItemView extends FrameLayout implements Cloneable {
    public MyItemView(@NonNull Context context) {
        super(context);
        initView();
    }

    public MyItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public View mView;
    public TextView mIdView;
    public TextView mContentView;

    private void initView() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item, this);
        mIdView = (TextView) mView.findViewById(R.id.item_number);
        mContentView = (TextView) mView.findViewById(R.id.content);
    }

    private static MyItemView instance;

    static MyItemView getCopyInstance(Context context){
        if(instance == null){
            instance = new MyItemView(context);
        }
        return instance.clone();
    }

    @NonNull
    @Override
    public MyItemView clone() {
        try {
            return (MyItemView) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            Log.d("kk", "--------------new------ViewHolder--------" );
        }
        return null;
    }
}
