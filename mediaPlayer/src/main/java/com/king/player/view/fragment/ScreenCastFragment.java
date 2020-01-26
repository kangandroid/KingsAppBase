package com.king.player.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.king.mobile.keling.DeviceViewModel;
import com.king.mobile.keling.DevicesManager;
import com.king.mobile.keling.PlayController;
import com.king.mobile.widget.BaseDialog;
import com.king.player.R;
import com.king.player.model.VideoInfo;
import com.king.player.view.widget.ChooseSpeedPopView;


public class ScreenCastFragment extends BaseDialog {
    private static final String TAG = "ScreenCastFragment";

    private VideoInfo videoInfo;
    private TextView deviceName;
    private TextView stateDesc;
    private ImageView actionControl;
    private TextView changeDevice;
    private TextView speedControl;
    private ImageView exit;
    private PlayController playController;
    private String speed = "1";
    private ChooseSpeedPopView speedSelector;

    public ScreenCastFragment(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }


    public void cast(FragmentManager fg) {
        show(fg, "ScreenCastFragment");
    }


    @Override
    protected int setLayoutId() {
        return R.layout.layout_screen_cast;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DevicesManager.getInstance().bindService(getActivity());
    }


    @Override
    protected void initView(View mRootView) {
        deviceName = mRootView.findViewById(R.id.device_name);
        stateDesc = mRootView.findViewById(R.id.state_desc);
        actionControl = mRootView.findViewById(R.id.play_control);
        speedControl = mRootView.findViewById(R.id.speed);
        speedControl.setOnClickListener(v -> showSpeedOption());
        exit = mRootView.findViewById(R.id.exit);
        exit.setOnClickListener(v -> {
            DevicesManager instance = DevicesManager.getInstance();
            instance.shutDown(getActivity());
            dismiss();
        });
        changeDevice = mRootView.findViewById(R.id.change_device);
        changeDevice.setOnClickListener(v -> {
            DevicesListFragment dm = new DevicesListFragment();
            dm.show(getFragmentManager(), TAG);
        });

        actionControl.setOnClickListener(v -> {
            if (playController != null) {
                playController.play(speed);
            }
        });
        DeviceViewModel dmv = ViewModelProviders.of(this).get(DeviceViewModel.class);
        dmv.getCastDevice().observe(this, device -> {
            if (device == null) {
                deviceName.setText("未连接设备");
            } else {
                String displayString = device.getDetails().getFriendlyName();
                deviceName.setText(displayString);
                stateDesc.setText("已连接");
                stateDesc.setTextColor(getResources().getColor(R.color.green));
                DevicesManager instance = DevicesManager.getInstance();
                playController = instance.getPlayController();
                playController.setSource(videoInfo.url);
            }
        });

    }

    private void showSpeedOption() {
        if (speedControl == null) {
            speedSelector = new ChooseSpeedPopView(getActivity());
            speedSelector.setOnChangeListener(s -> {
                this.speed = s;
                speedControl.setText(s);
            });
            speedSelector.setDefaultSpeed(speed);
            speedSelector.showAsDropDown(speedControl, 0, -600);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
