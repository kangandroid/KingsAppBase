package com.king.player.datasource;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.king.mobile.util.Loker;
import com.king.player.model.VideoInfo;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class ClipboardVideoSource {

    public static final String regex = "";
    public static final String sample = "";
    public static void checkClipboard(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        // If the clipboard doesn't contain data, disable the paste menu item.
        // If it does contain data, decide if you can handle the data.
        if (clipboard.hasPrimaryClip() && (clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            String copyText = item.getText().toString();
            Loker.d(copyText);
            if(!TextUtils.isEmpty(copyText)&& copyText.matches(regex)){
                Gson gson = new GsonBuilder().create();
                gson.fromJson(copyText, VideoInfo.class);
            }

        }
    }
}
