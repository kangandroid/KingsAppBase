package com.king.mobile.keling;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.fourthline.cling.model.meta.Device;

import java.util.List;

public class DeviceViewModel extends AndroidViewModel {
    private MutableLiveData<List<Device>> deviceList;
    private MutableLiveData<Device> linkedDevice;

    public DeviceViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Device>> getDeviceList() {
        return DevicesManager.getInstance().getDeviceList();
    }

    public LiveData<Device> getCastDevice() {
        return DevicesManager.getInstance().getLinkedDevice();
    }
}
