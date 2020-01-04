package com.king.player;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.util.BindLayout;
import com.king.mobile.util.ImageUtil;
import com.king.player.model.VideoInfo;

import java.util.List;

public class LocalVideoListAdapter extends BaseListAdapter<VideoInfo> {
    public LocalVideoListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindVHLayoutId(List VhClazzList) {
        VhClazzList.add(VideoVieHolder.class);
    }


    @BindLayout(id = R.layout.item_local_video)
    static class VideoVieHolder extends BaseViewHolder<VideoInfo> {

        private final TextView tvName;
        private final TextView tvDesc;
        private final ImageView ivIcon;

        public VideoVieHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            ivIcon = itemView.findViewById(R.id.icon);
        }

        @Override
        protected void bindView(VideoInfo bean, int position, Context context) {
            ImageUtil.loadCover(ivIcon, bean.url);
            tvName.setText(bean.name);
            tvDesc.setText(bean.desc);
        }
    }
}
