package com.king.mobile.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageUtil {
    public static void loadImage(ImageView view, String url) {
        Glide.with(view).load(url).into(view);
    }

    /**
     * 加载第四秒的帧数作为封面
     * url就是视频的地址
     */
    public static void loadCover(ImageView view, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .frame(4000000)
                .centerCrop();
        Glide.with(view)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(view);
    }
}
