package com.king.player.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.util.BindLayout;
import com.king.mobile.util.ImageUtil;
import com.king.player.R;
import com.king.player.model.VideoInfo;

import java.util.List;

public class RecentVideoListAdapter extends BaseListAdapter<VideoInfo> {
    public RecentVideoListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindVHLayoutId(List VhClazzList) {
        VhClazzList.add(VideoVieHolder.class);
    }


    @BindLayout(id = R.layout.item_recent_video)
    static class VideoVieHolder extends BaseViewHolder<VideoInfo> {

        private final TextView tvName;
        private final ProgressBar progressBar;
        private final ImageView ivIcon;

        public VideoVieHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            progressBar = itemView.findViewById(R.id.progressBar);
            ivIcon = itemView.findViewById(R.id.icon);
        }

        @Override
        protected void bindView(VideoInfo bean, int position, Context context) {
            ImageUtil.loadCover(ivIcon, bean.url);
            tvName.setText(bean.name);
            progressBar.setMax(bean.progress);
            progressBar.setProgress(100);
        }
    }
}
