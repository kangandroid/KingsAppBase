package com.king.mobile.keling;

import com.king.mobile.util.Loker;

import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.RegistryListener;

/**
 * Registry 注册表 RegistryListener监听设备加入以及退出 设置给Registry
 */
public class DevicesListener implements RegistryListener {

    /**
     *
     * @param registry
     * @param device
     */
    @Override
    public void remoteDeviceDiscoveryStarted(Registry registry, RemoteDevice device) {
        Loker.d("remoteDeviceDiscoveryStarted:"+device.getDisplayString());
    }

    @Override
    public void remoteDeviceDiscoveryFailed(Registry registry, RemoteDevice device, Exception ex) {
        Loker.d("remoteDeviceDiscoveryFailed:"+device.getDisplayString());
    }

    @Override
    public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
        Loker.d("remoteDeviceAdded:"+device.getDisplayString());
    }

    @Override
    public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
        Loker.d("remoteDeviceUpdated: "+device.getDisplayString());
    }

    @Override
    public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
        Loker.d("remoteDeviceRemoved: "+device.getDisplayString());
    }

    @Override
    public void localDeviceAdded(Registry registry, LocalDevice device) {
        Loker.d("localDeviceAdded: "+device.getDisplayString());
    }

    @Override
    public void localDeviceRemoved(Registry registry, LocalDevice device) {
        Loker.d("localDeviceRemoved: "+device.getDisplayString());
    }

    @Override
    public void beforeShutdown(Registry registry) {
        Loker.d("remoteDeviceDiscoveryStarted");
    }

    @Override
    public void afterShutdown() {
        Loker.d("----------afterShutdown--------");

    }
}
