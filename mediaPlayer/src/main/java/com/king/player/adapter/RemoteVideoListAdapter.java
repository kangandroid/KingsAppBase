package com.king.player.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.util.BindLayout;
import com.king.mobile.util.ImageUtil;
import com.king.mobile.widget.CommonDialog;
import com.king.player.R;
import com.king.player.model.VideoInfo;
import com.king.player.view.widget.ActionPopView;

import java.util.List;

public class RemoteVideoListAdapter extends BaseListAdapter<VideoInfo> {
    Context mContext;

    public RemoteVideoListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onBindVHLayoutId(List VhClazzList) {
        VhClazzList.add(VideoVieHolder.class);
    }


    @BindLayout(id = R.layout.item_video_info)
    static class VideoVieHolder extends BaseViewHolder<VideoInfo> {

        private final TextView tvName;
        private final TextView tvDesc;
        private final TextView tvStatus;
        private final ImageView ivIcon;
        private final ImageView action;

        public VideoVieHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvDesc = itemView.findViewById(R.id.desc);
            ivIcon = itemView.findViewById(R.id.cover);
            tvStatus = itemView.findViewById(R.id.status);
            action = itemView.findViewById(R.id.action);
        }

        @Override
        protected void bindView(VideoInfo bean, int position, Context context) {
            ImageUtil.loadCover(ivIcon, bean.url);
            tvName.setText(bean.name);
            tvDesc.setText(bean.desc);
            if (TextUtils.isEmpty(bean.state)) {
                tvStatus.setVisibility(View.GONE);
            } else {
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText(bean.state);
            }
            action.setOnClickListener(v -> showPop(v, bean));
        }

        private void showPop(View v, VideoInfo bean) {
            new ActionPopView(getContext(), bean).showAsDropDown(v,-20,-120);
        }
    }
}
