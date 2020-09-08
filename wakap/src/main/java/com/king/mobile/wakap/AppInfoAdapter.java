package com.king.mobile.wakap;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.util.BindLayout;
import com.king.mobile.wakap.model.AppInfo;

import java.text.ParseException;
import java.util.List;

public class AppInfoAdapter extends BaseListAdapter<AppInfo> {
    private AppInfo selectedApp;

    public AppInfoAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindVHLayoutId(List<Class<?>> VhClazzList) {
        VhClazzList.add(AppInfoViewHolder.class);
    }

    @BindLayout(id = R.layout.item_app_info)
    static class AppInfoViewHolder extends BaseViewHolder<AppInfo> {

        private final ImageView appIcon;
        private final TextView appName;

        AppInfoViewHolder(View view) {
            super(view);
            appIcon = view.findViewById(R.id.app_icon);
            appName = view.findViewById(R.id.app_name);
        }

        @Override
        protected void bindView(AppInfo bean, int position, Context context) throws ParseException {
            appIcon.setImageDrawable(bean.icon);
            appName.setText(bean.appName);
        }

    }
}
