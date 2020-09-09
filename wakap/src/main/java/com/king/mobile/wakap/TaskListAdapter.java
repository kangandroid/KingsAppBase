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
import java.util.Locale;

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
            tvTime.setText(String.format(Locale.CHINA, "%d年%d月%d日\r\n%d时%d分",
                    instance.get(Calendar.YEAR),
                    instance.get(Calendar.MONTH) + 1,
                    instance.get(Calendar.DAY_OF_MONTH),
                    instance.get(Calendar.HOUR_OF_DAY),
                    instance.get(Calendar.MINUTE)));
            AppInfo appInfo = PackageUtils.getAppInfo(bean.targetPackageName);
            appName.setText(appInfo.appName);
            appIcon.setImageDrawable(appInfo.icon);
        }

    }
}
