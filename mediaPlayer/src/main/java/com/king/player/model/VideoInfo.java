package com.king.player.model;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.Serializable;

@Entity(tableName = "tab_videos", primaryKeys = {"id", "url"})
public class VideoInfo implements Serializable {
    @Ignore
    private static final long serialVersionUID = 8822818790694831649L;
    public long id;
    @NonNull
    @ColumnInfo(name = "url")  // 名称
    public String url;
    @ColumnInfo(name = "name")  // 名称
    public String name;
    @ColumnInfo(name = "desc") // 描述
    public String desc;
    @ColumnInfo(name = "create_time") // 创建实际
    public long createTime;
    @ColumnInfo(name = "state", defaultValue = "OK") // 状态 默认可用 尝试播放不能用后标记
    public String state;
    @ColumnInfo(name = "tags") // 标记
    public String tags;
    @ColumnInfo(name = "type") // 类型
    public String type;
    @ColumnInfo(name = "local_path") // 下载后本地路径
    public String localPath;
    @ColumnInfo(name = "local_id") // 本地媒体库
    public String localId;
    @ColumnInfo(name = "date_added") // 添加时间
    public long dateAdded;
    @ColumnInfo(name = "date_modified") // 修改时间
    public long dateModified;
    @ColumnInfo(name = "size") // 视频大小
    public long size;

    @ColumnInfo(name = "duration")
    public int duration; // 秒
    @ColumnInfo(name = "progress")
    public int progress; // 秒
    @ColumnInfo(name = "latest_play_time")
    public long latestPlayTime;
    @ColumnInfo(name = "speed")// 秒
    public float speed; // 播放速度

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof VideoInfo) {
            VideoInfo other = (VideoInfo) obj;
            return TextUtils.equals(other.localId, this.localId) || TextUtils.equals(other.url, this.url);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", createTime=" + createTime +
                ", url='" + url + '\'' +
                ", state='" + state + '\'' +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                ", localPath='" + localPath + '\'' +
                ", localId='" + localId + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", size=" + size +
                '}';
    }
}
