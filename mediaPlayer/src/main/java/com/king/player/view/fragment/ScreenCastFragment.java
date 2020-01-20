package com.king.player.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.king.mobile.keling.DeviceViewModel;
import com.king.mobile.keling.DevicesManager;
import com.king.mobile.util.Loker;
import com.king.mobile.widget.BaseDialog;
import com.king.player.R;


public class ScreenCastFragment extends BaseDialog {
    private static final String TAG = "ScreenCastFragment";


    private TextView deviceName;
    private TextView stateDesc;
    private TextView actionControl;
    private TextView changeDevice;
    private ImageView exit;

    public ScreenCastFragment() {
    }


    public void cast(FragmentManager fg) {
        show(fg, "ScreenCastFragment");
    }


    private class InstanceHolder {
        private ScreenCastFragment instance = new ScreenCastFragment();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_screen_cast;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DevicesManager.getInstance().bindService(getActivity());
        Loker.d("bindService--------------");
    }


    @Override
    protected void initView(View mRootView) {
        deviceName = mRootView.findViewById(R.id.device_name);
        stateDesc = mRootView.findViewById(R.id.state_desc);
        actionControl = mRootView.findViewById(R.id.action_control);
        exit = mRootView.findViewById(R.id.exit);
        changeDevice = mRootView.findViewById(R.id.change_device);
        changeDevice.setOnClickListener(v -> {
            DevicesListFragment dm = new DevicesListFragment();
            dm.show(getFragmentManager(), TAG);
        });
        DeviceViewModel dmv = ViewModelProviders.of(this).get(DeviceViewModel.class);
        dmv.getCastDevice().observe(this, device -> {
            if (device == null) {
                deviceName.setText("未连接设备");
            } else {
                String displayString = device.getDisplayString();
                deviceName.setText(displayString);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
