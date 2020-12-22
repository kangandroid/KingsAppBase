package com.king.mobile.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

class NetWorkListener {

    void getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.isActiveNetworkMetered()) {
            //返回当前活动数据网络是否按流量计费。
            // 当用户因金钱成本、数据限制或电池/性能问题而对该连接上的大量数据使用敏感时，
            // 网络被归类为计量网络。您应该在进行大型数据传输之前检查此项，
            // 并警告用户或延迟操作，直到另一个网络可用
        }

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        NetworkInfo.State state = networkInfo.getState();
        switch (state) {
            case DISCONNECTED:// 为连接
            case CONNECTING:// 链接中
            case CONNECTED: // 已连接
            case SUSPENDED: // 暂停
            case DISCONNECTING: // 断开中
            case UNKNOWN: // 位知
        }

        cm.addDefaultNetworkActiveListener(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network activeNetwork = cm.getActiveNetwork();
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(activeNetwork);
                int upKbps = capabilities.getLinkUpstreamBandwidthKbps(); // 上传网速
                int downKbps = capabilities.getLinkDownstreamBandwidthKbps(); // 下载网速
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback());
        }
    }


}
