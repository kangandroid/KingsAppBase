package com.king.mobile.testapp.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class DataDiffCallback extends DiffUtil.ItemCallback<DiffI> {

    @Override
    public boolean areItemsTheSame(@NonNull DiffI oldItem, @NonNull DiffI newItem) {
        return oldItem.isSameOne(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull DiffI oldItem, @NonNull DiffI newItem) {
        return oldItem.isContentSame(newItem);
    }
}
