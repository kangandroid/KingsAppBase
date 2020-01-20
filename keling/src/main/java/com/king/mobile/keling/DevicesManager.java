package com.king.mobile.keling;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;

import com.king.mobile.util.Loker;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.android.AndroidRouter;
import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.transport.Router;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DevicesManager {

    private ControlPoint controlPoint;

    private PlayController playController;

    private UpnpService upnpService;

    private AndroidRouter androidRouter;

    private ServiceConnection upnpConnection = new ServiceConnection() {


        public void onServiceConnected(ComponentName className, IBinder service) {
            Loker.d("DevicesManager ======= onServiceConnected");
            if (service instanceof AndroidUpnpService) { // 连接
                Loker.d("DevicesManager ======= instanceof AndroidUpnpService");
                upnpService = ((AndroidUpnpService) service).get();
                Router router = upnpService.getRouter();
                if ( router instanceof AndroidRouter){
                     androidRouter = (AndroidRouter) router;
                }
                controlPoint = upnpService.getControlPoint();
                Registry registry = upnpService.getRegistry();
                getDevices(registry);
                registry.addListener(new DevicesListener());
            }
        }


        public void onServiceDisconnected(ComponentName className) {
            upnpService = null;
        }
    };

    public MutableLiveData<Device> getLinkedDevice() {
        return linkedDevice;
    }

    public MutableLiveData<List<Device>> getDeviceList() {
        return deviceList;
    }

    private MutableLiveData<Device> linkedDevice;
    private MutableLiveData<List<Device>> deviceList;

    private DevicesManager() {
        linkedDevice = new MutableLiveData<>();
        deviceList = new MutableLiveData<>();
    }

    private static class InstanceHolder {
        private static DevicesManager instance = new DevicesManager();
    }

    public static DevicesManager getInstance() {
        return DevicesManager.InstanceHolder.instance;
    }

    private void getDevices(Registry registry) {
        Loker.d("getDevices =======");
        List<Device> deviceArrayList = new ArrayList<>();
        Collection<Device> devices = registry.getDevices();
        Loker.d("devices size ======="+ devices.size());
        Iterator<Device> iterator = devices.iterator();
        while (iterator.hasNext()){
            Device device = iterator.next();
            deviceArrayList.add(device);
            Loker.d("device.getDisplayString =======" + device.getDisplayString());
        }
        deviceList.postValue(deviceArrayList);
    }

    public boolean isConnected() {
        return upnpService != null;
    }

    public void cast(Device device) {
        if (isConnected()) {
            playController = new PlayController(controlPoint, device);
            playController.play("1");
        }
        linkedDevice.postValue(device);
    }

    public PlayController getPlayControler() {
        return playController;
    }

    public void bindService(FragmentActivity activity) {
        Intent intent = new Intent(activity, UpnpClient.class);
        activity.bindService(intent, upnpConnection, Context.BIND_AUTO_CREATE);
    }

    public void shutDown(FragmentActivity activity) {
        if (upnpService != null) {
            activity.unbindService(upnpConnection);
            upnpService = null;
        }
    }
}
