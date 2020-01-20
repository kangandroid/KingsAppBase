package com.king.player.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.keling.DevicesManager;
import com.king.mobile.keling.PlayController;
import com.king.mobile.util.BindLayout;
import com.king.player.R;

import org.fourthline.cling.model.meta.Device;

import java.util.List;

public class DeviceListAdapter extends BaseListAdapter<Device> {

    public DeviceListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindVHLayoutId(List VhClazzList) {
        VhClazzList.add(DeviceViewHolder.class);
    }

    @BindLayout(id = R.layout.item_device_list)
    static class DeviceViewHolder extends BaseListAdapter.BaseViewHolder<Device> {

        private TextView name;
        private ImageView ivLinked;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            ivLinked = itemView.findViewById(R.id.iv_linked);
        }

        @Override
        protected void bindView(Device bean, int position, Context context) {
            name.setText(bean.getDisplayString());
            PlayController pc = DevicesManager.getInstance().getPlayControler();
            if (pc != null) {
                Device device = pc.getDevice();
                if (bean.getIdentity().equals(device.getIdentity())) {
                    ivLinked.setVisibility(View.VISIBLE);
                } else {
                    ivLinked.setVisibility(View.INVISIBLE);
                }

            }
        }
    }
}
