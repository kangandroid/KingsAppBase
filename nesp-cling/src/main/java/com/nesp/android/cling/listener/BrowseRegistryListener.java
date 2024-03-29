package com.nesp.android.cling.listener;

import android.util.Log;

import com.nesp.android.cling.entity.ClingDevice;
import com.nesp.android.cling.entity.ClingDeviceList;
import com.nesp.android.cling.service.manager.ClingManager;
import com.nesp.android.cling.util.Utils;

import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.DefaultRegistryListener;
import org.fourthline.cling.registry.Registry;

public class BrowseRegistryListener extends DefaultRegistryListener {


    private static final String TAG = BrowseRegistryListener.class.getSimpleName();

    private DeviceListChangedListener mOnDeviceListChangedListener;

    /* Discovery performance optimization for very slow Android devices! */
    @Override
    public void remoteDeviceDiscoveryStarted(Registry registry, RemoteDevice device) {
        // 在这里设备拥有服务 也木有 action。。
//        deviceAdded(device);
    }

    @Override
    public void remoteDeviceDiscoveryFailed(Registry registry, final RemoteDevice device, final Exception ex) {
        Log.e(TAG, "remoteDeviceDiscoveryFailed device: " + device.getDisplayString());
        deviceRemoved(device);
    }
    /* End of optimization, you can remove the whole block if your Android handset is fast (>= 600 Mhz) */

    @Override
    public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
         deviceAdded(device);
    }

    @Override
    public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
        deviceRemoved(device);
    }

    @Override
    public void localDeviceAdded(Registry registry, LocalDevice device) {
                deviceAdded(device); // 本地设备 已加入
    }

    @Override
    public void localDeviceRemoved(Registry registry, LocalDevice device) {
                deviceRemoved(device); // 本地设备 已移除
    }

    private void deviceAdded(Device device) {
        Log.e(TAG, "deviceAdded");
        if (!device.getType().equals(ClingManager.DMR_DEVICE_TYPE)) {
            Log.e(TAG, "deviceAdded called, but not match");
            return;
        }

        if (Utils.isNotNull(mOnDeviceListChangedListener)) {
            ClingDevice clingDevice = new ClingDevice(device);
            ClingDeviceList.getInstance().addDevice(clingDevice);
            mOnDeviceListChangedListener.onDeviceAdded(clingDevice);
        }
    }

    private void deviceRemoved(Device device) {
        Log.e(TAG, "deviceRemoved");
        if (Utils.isNotNull(mOnDeviceListChangedListener)) {
            ClingDevice clingDevice = ClingDeviceList.getInstance().getClingDevice(device);
            if (clingDevice != null) {
                ClingDeviceList.getInstance().removeDevice(clingDevice);
                mOnDeviceListChangedListener.onDeviceRemoved(clingDevice);
            }
        }
    }

    public void setOnDeviceListChangedListener(DeviceListChangedListener onDeviceListChangedListener) {
        mOnDeviceListChangedListener = onDeviceListChangedListener;
    }
}
