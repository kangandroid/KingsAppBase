package com.king.mobile.wakap;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.util.BindLayout;
import com.king.mobile.wakap.model.AppInfo;
import com.king.mobile.wakap.model.Task;
import com.king.mobile.wakap.util.PackageUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

class TaskListAdapter extends BaseListAdapter<Task> {
    public TaskListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindVHLayoutId(List<Class<?>> VhClazzList) {
        VhClazzList.add(TaskViewHolder.class);
    }

    @BindLayout(id = R.layout.item_task)
    static class TaskViewHolder extends BaseViewHolder<Task> {

        private final ImageView appIcon;
        private final TextView appName;
        private final TextView tvTime;

        TaskViewHolder(View view) {
            super(view);
            tvTime = view.findViewById(R.id.tv_time);
            appIcon = view.findViewById(R.id.app_icon);
            appName = view.findViewById(R.id.app_name);
        }

        @Override
        protected void bindView(Task bean, int position, Context context) throws ParseException {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(bean.triggerAtMillis);
            tvTime.setText(instance.get(Calendar.YEAR) + "年"
                    + (instance.get(Calendar.MONTH) + 1) + "月"
                    + instance.get(Calendar.DAY_OF_MONTH) + "日 "
                    + instance.get(Calendar.HOUR_OF_DAY) + "时"
                    + instance.get(Calendar.MINUTE) + "分");
            AppInfo appInfo = PackageUtils.getAppInfo(bean.targetPackageName);
            appName.setText(appInfo.appName);
            appIcon.setImageDrawable(appInfo.icon);
        }

    }
}
