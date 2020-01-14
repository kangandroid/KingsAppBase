package com.king.mobile.util;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
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
        Bitmap bitmap = null;
        if (url.startsWith("/")){
            bitmap = getVideoCover(url);
            if (bitmap != null){
                Glide.with(view).load(bitmap).into(view);
            } else {
                RequestOptions requestOptions = new RequestOptions()
                        .frame(4000000)
                        .centerCrop();
                Glide.with(view)
                        .setDefaultRequestOptions(requestOptions)
                        .load(url)
                        .into(view);
            }
        } else if(url.contains(".m3u8")) {

        }

    }

    /**
     * 获取视频文件截图
     *
     * @param path 视频文件的路径
     * @return Bitmap 返回获取的Bitmap
     * MINI_KIND		FULL_SCREEN_KIND		 MICRO_KIND
     */
    public static Bitmap getVideoCover(String path) {
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);
        if (bitmap == null) {
            //提取到的视频封面为空
            bitmap = getVideoThumb(path);
            if (bitmap == null){
                ToastUtil.show("获取失败");
            }
        }
        return bitmap;
    }

    /**
     * 获取视频文件截图
     *
     * @param path 视频文件的路径
     * @return Bitmap 返回获取的Bitmap
     */
    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        Bitmap frameAtTime = media.getFrameAtTime(1 * 1000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST);
        if (frameAtTime == null) {
            frameAtTime = media.getFrameAtTime(3 * 1000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST);
        }
        return frameAtTime;
    }
}
