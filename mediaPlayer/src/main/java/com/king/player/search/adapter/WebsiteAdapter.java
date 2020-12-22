package com.king.player.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.king.player.R;
import com.king.player.search.WebSiteInfo;

public class WebsiteAdapter extends ListAdapter<WebSiteInfo, WebsiteVieHolder> {

    public WebsiteAdapter() {
        super(new WebsiteInfoDiff());
    }

    @NonNull
    @Override
    public WebsiteVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_website, null);
        return new WebsiteVieHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteVieHolder holder, int position) {
        holder.bindData(getItem(position));
    }

}
