package com.king.player.search.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.king.player.search.WebSiteInfo;

class WebsiteInfoDiff extends DiffUtil.ItemCallback<WebSiteInfo> {

    @Override
    public boolean areItemsTheSame(@NonNull WebSiteInfo oldItem, @NonNull WebSiteInfo newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull WebSiteInfo oldItem, @NonNull WebSiteInfo newItem) {
        return oldItem.title.equals(newItem.title) && oldItem.title.equals(newItem.url);
    }
}

