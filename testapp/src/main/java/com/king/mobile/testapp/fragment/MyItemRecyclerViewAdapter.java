package com.king.mobile.testapp.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.mobile.testapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyContent.DummyItem}.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;

    public MyItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
        mValues = items;
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("kk", "--------------------onBindViewHolder--------" + position);
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(ViewGroup view) {
            super(view);
            MyItemView instance = new MyItemView(view.getContext());
            view.addView(instance);
            mView = instance.mView;
            mIdView = instance.mIdView;
            mContentView =instance.mContentView;
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}

