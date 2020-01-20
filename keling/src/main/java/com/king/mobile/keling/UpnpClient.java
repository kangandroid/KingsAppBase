package com.king.mobile.keling;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.king.mobile.util.Loker;

import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidRouter;
import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.protocol.ProtocolFactory;

/**
 * 提供一个UPnP堆栈，作为Android应用程序服务组件配置
 * 在实例化时发送对所有UPnP设备的搜索
 */
public class UpnpClient extends AndroidUpnpServiceImpl {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected AndroidRouter createRouter(UpnpServiceConfiguration configuration, ProtocolFactory protocolFactory, Context context) {
        AndroidRouter router = super.createRouter(configuration, protocolFactory, context);
        return router;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
