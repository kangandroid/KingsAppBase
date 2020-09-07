package com.king.player.view.fragment;

import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseApplication;
import com.king.mobile.keling.DeviceViewModel;
import com.king.mobile.keling.DevicesManager;
import com.king.player.R;
import com.king.player.adapter.DeviceListAdapter;

import com.king.mobile.util.ScreenUtils;
import com.king.mobile.widget.BaseDialog;

public class DevicesListFragment extends BaseDialog {

    public DevicesListFragment() {
        dialogPosition = DIALOG_POSITION_RIGHT;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.layout_device_list;
    }

    @Override
    protected void initView(View mRootView) {
        FragmentActivity activity = getActivity();
        int screenWidth = ScreenUtils.getScreenWidth(BaseApplication.getContext());
        boolean landscape = ScreenUtils.isLandscape(activity);
        if (landscape) {
            height = screenWidth / 2;
        } else {
            height = screenWidth;
        }
        RecyclerView devicesList = mRootView.findViewById(R.id.device_list);
        devicesList.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        final DeviceListAdapter adapter = new DeviceListAdapter(activity);
        devicesList.setAdapter(adapter);
        DevicesManager dm = DevicesManager.getInstance();
        DeviceViewModel dmv = ViewModelProviders.of(this).get(DeviceViewModel.class);
        dmv.getDeviceList().observe(this, adapter::setData);
        adapter.setOnItemClickListener((device, view, i) -> {
            dm.setDevice(device);
            dismiss();
        });
    }
}
