package com.king.player.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.util.BindLayout;
import com.king.player.R;
import com.king.player.video.VideoInfo;

import java.util.List;

public class LiveTVListAdapter extends BaseListAdapter<VideoInfo> {
    public LiveTVListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindVHLayoutId(List VhClazzList) {
        VhClazzList.add(VideoVieHolder.class);
    }


    @BindLayout(id = R.layout.item_live_tv)
    static class VideoVieHolder extends BaseViewHolder<VideoInfo> {

        private final TextView tvName;

        public VideoVieHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);

        }

        @Override
        protected void bindView(VideoInfo bean, int position, Context context) {
            tvName.setText(bean.name);
        }
    }
}
