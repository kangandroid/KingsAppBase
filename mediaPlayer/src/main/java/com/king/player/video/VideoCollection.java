package com.king.player.video;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tab_collection")
public class VideoCollection implements Serializable {
    @Ignore
    private static final long serialVersionUID = 8822818790694831649L;

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "desc")
    public String desc;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "video_ids")
    public String videoIds;
    @ColumnInfo(name = "cover_url")
    public String coverUrl;
    @ColumnInfo(name = "date_created")
    public long dateAdded;
    @ColumnInfo(name = "date_modified")
    public long dateModified;

    @Override
    public String toString() {
        return "VideoCollection{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", videoIds='" + videoIds + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}
