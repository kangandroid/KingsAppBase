package com.king.mobile.wakap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.util.Loker;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.wakap.model.AppInfo;
import com.king.mobile.wakap.model.Task;
import com.king.mobile.wakap.util.AlarmUtils;
import com.king.mobile.wakap.util.PackageUtils;
import com.king.mobile.widget.TitleBar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskCreateActivity extends BaseActivity {
    private TaskViewModel viewModel;

    private TimePickerDialog timePickerDialog;
    private TimePickerDialog intervalTimePicker;
    private DatePickerDialog datePickerDialog;
    private AppInfo appInfo;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvInterval;
    private TextView tvAppName;
    private boolean isRepeat;
    private int hourOfDay;
    private int minute;
    private int year;
    private int month;
    private int dayOfMonth;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitleBarColorRes(R.color.colorPrimaryDark)
                .setTitle(R.string.cread_alarm)
                .setTitleTextColor(R.color.colorAccent)
                .invalidate();
    }

    @Override
    protected void initView() {
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        timePickerDialog = new TimePickerDialog(this, (TimePicker view, int hourOfDay, int minute) -> {
            tvTime.setText(String.format("%d时%d分", hourOfDay, minute));
            this.hourOfDay = hourOfDay;
            this.minute = minute;
        }, 0, 0, true);
        intervalTimePicker = new TimePickerDialog(this, (TimePicker view, int hourOfDay, int minute) -> {
            tvInterval.setText(String.format("%d时%d分", hourOfDay, minute));
        }, 0, 0, true);
        Calendar instance = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, (DatePicker view, int year, int month, int dayOfMonth) -> {
            tvDate.setText(String.format("%d年%d月%d日", year, month + 1, dayOfMonth));
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
        }, instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvInterval = findViewById(R.id.tvInterval);
        tvAppName = findViewById(R.id.tv_app_name);
        Switch repeatable = findViewById(R.id.repeatable);
        View llInterval = findViewById(R.id.llInterval);
        llInterval.setOnClickListener(v -> intervalTimePicker.show());
        repeatable.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            isRepeat = isChecked;
            llInterval.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
        findViewById(R.id.llDate).setOnClickListener(v -> datePickerDialog.show());
        findViewById(R.id.llTime).setOnClickListener(v -> timePickerDialog.show());
        findViewById(R.id.llSelectApK).setOnClickListener(v -> startActivityForResult(new Intent(this, InstallAppActivity.class), 1));
        findViewById(R.id.create).setOnClickListener(v -> {
            if (TextUtils.isEmpty(tvDate.getText())) {
                ToastUtil.show("请选择开始的日期");
                return;
            }
            if (TextUtils.isEmpty(tvTime.getText())) {
                ToastUtil.show("请选择开始的时间");
                return;
            }
            if (isRepeat && TextUtils.isEmpty(tvInterval.getText())) {
                ToastUtil.show("请选择时间间隔");
                return;
            }
            if (TextUtils.isEmpty(tvAppName.getText())) {
                ToastUtil.show("请选择要打开的应用");
                return;
            }
            Task task = new Task();
            task.name = "定时极速打卡";
            Calendar calendarA = Calendar.getInstance();
            calendarA.clear();
            calendarA.set(year, month, dayOfMonth, hourOfDay, minute);
            long timeInMillis = calendarA.getTimeInMillis();
            Loker.d("timeInMillis=" + timeInMillis);
            Loker.d("getDisplayName==" + calendarA.getTime().toString());
            task.triggerAtMillis = timeInMillis;
            task.targetPackageName = appInfo.appName;
            task.targetPackageName = appInfo.packageName;
            Disposable subscribe = Observable.just(task)
                    .map((Task t) -> {
                        AlarmUtils.setOneShotAlarm(this, t);
                        calendarA.add(Calendar.MINUTE, 1);
                        AlarmUtils.setOpenSelf(this, calendarA.getTimeInMillis());
                        taskViewModel.insert(t);
                        return true;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            (success) -> {
                                if (success) {
                                    finish();
                                }
                            },
                            (e) -> Loker.e("---------", e.toString())
                    );
            Loker.e("subscribe.isDisposed()---------" + subscribe.isDisposed());
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 100) {
            String packageName = data.getCharSequenceExtra("packageName").toString();
            appInfo = PackageUtils.getAppInfo(packageName);
            tvAppName.setText(appInfo.appName);
        }
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.create_task;
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }
}
