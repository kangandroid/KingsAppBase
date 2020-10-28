package com.king.mobile.wakap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.util.Loker;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.wakap.databinding.CreateTaskBinding;
import com.king.mobile.wakap.model.AppInfo;
import com.king.mobile.wakap.model.Task;
import com.king.mobile.wakap.util.AlarmUtils;
import com.king.mobile.wakap.util.PackageUtils;
import com.king.mobile.widget.TitleBar;

import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskCreateActivity extends BaseActivity {
    private TaskViewModel viewModel;

    private TimePickerDialog timePickerDialog;
    private TimePickerDialog intervalTimePicker;
    private DatePickerDialog datePickerDialog;
    private AppInfo appInfo;
    private boolean isRepeat;
    private int hourOfDay;
    private int minute;
    private int year;
    private int month;
    private int dayOfMonth;
    private TaskViewModel taskViewModel;
    private com.king.mobile.wakap.databinding.CreateTaskBinding binding;

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
            binding.tvTime.setText(String.format("%d时%d分", hourOfDay, minute));
            this.hourOfDay = hourOfDay;
            this.minute = minute;
        }, 0, 0, true);
        intervalTimePicker = new TimePickerDialog(this, (TimePicker view, int hourOfDay, int minute) -> {
            binding.tvInterval.setText(String.format("%d时%d分", hourOfDay, minute));
        }, 0, 0, true);
        Calendar instance = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, (DatePicker view, int year, int month, int dayOfMonth) -> {
            binding.tvDate.setText(String.format("%d年%d月%d日", year, month + 1, dayOfMonth));
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
        }, instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
        binding.llInterval.setOnClickListener(v -> intervalTimePicker.show());
        binding.repeatable.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            isRepeat = isChecked;
            binding.llInterval.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
        binding.llDate.setOnClickListener(v -> datePickerDialog.show());
        binding.llTime.setOnClickListener(v -> timePickerDialog.show());
        binding.llSelectApK.setOnClickListener(v -> startActivityForResult(new Intent(this, InstallAppActivity.class), 1));
        binding.create.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.tvDate.getText())) {
                ToastUtil.show("请选择开始的日期");
                return;
            }
            if (TextUtils.isEmpty(binding.tvTime.getText())) {
                ToastUtil.show("请选择开始的时间");
                return;
            }
            if (isRepeat && TextUtils.isEmpty(binding.tvInterval.getText())) {
                ToastUtil.show("请选择时间间隔");
                return;
            }
            if (TextUtils.isEmpty(binding.tvAppName.getText())) {
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
                            (e) -> Loker.e(e.toString())
                    );
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 100) {
            String packageName = data.getCharSequenceExtra("packageName").toString();
            appInfo = PackageUtils.getAppInfo(packageName);
            binding.tvAppName.setText(appInfo.appName);
        }
    }


    @Override
    protected int getContentLayoutId() {
        binding = CreateTaskBinding.inflate(getLayoutInflater(), mContainer, true);
        return 0;
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }
}
