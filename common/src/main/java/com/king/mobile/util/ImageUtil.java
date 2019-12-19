package com.king.mobile.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {
    public static void loadImage(ImageView view, String url) {
        Glide.with(view).load(url).into(view);
    }

}
