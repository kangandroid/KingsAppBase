package com.king.mobile.testapp.fragment;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

abstract class BaseAdapter extends ListAdapter<DiffI, BaseAdapter.TestVH> {

    List<DiffI> data;

    protected BaseAdapter() {
        super(new DataDiffCallback());
    }

    abstract TestVH getHolderInstance();

    @NonNull
    @Override
    public TestVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestVH holderInstance = getHolderInstance();
        if (holderInstance == null) {
            throw new RuntimeException("");
        }
        try {
            return holderInstance.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return holderInstance;
    }

    @Override
    public void onBindViewHolder(@NonNull TestVH holder, int position) {

    }

    abstract class TestVH extends RecyclerView.ViewHolder implements Cloneable {

        public TestVH(@NonNull View itemView) {
            super(itemView);
        }

        @NonNull
        @Override
        public TestVH clone() throws CloneNotSupportedException {
            return (TestVH) super.clone();
        }

        protected abstract void bindData(DiffI data);

    }

}
