package com.king.mobile.wakap;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.wakap.util.AlarmUtils;
import com.king.mobile.widget.TitleBar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.migration.OptionalInject;

public class MainActivity extends BaseActivity {
    private TaskViewModel taskViewModel;

    @Inject
    private RecyclerView list;
    private TaskListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitleBarColorRes(R.color.colorPrimaryDark)
                .setTitle(R.string.app_name)
                .setTitleTextColor(R.color.colorAccent)
                .invalidate();
    }

    @Override
    protected void initView() {
        ImageButton btnAddAlarm = findViewById(R.id.add_alarm);
        btnAddAlarm.setOnClickListener(v -> startActivity(new Intent(this, TaskCreateActivity.class)));
        list = findViewById(R.id.recycler_view);
        list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        adapter = new TaskListAdapter(this);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getTasks().observe(this, adapter::setData);
        adapter.setOnItemLongClickListener((task, view, position) -> {
            boolean happened = System.currentTimeMillis() > task.triggerAtMillis;
            String action = happened ? "删除" : "取消";
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("是否" + action + "该任务？")
                    .setNegativeButton("Let me see", (dialog12, which) -> {
                        dialog12.dismiss();
                    })
                    .setPositiveButton(action, (dialog1, which) -> {
                        if (!happened) {
                            AlarmUtils.cancelAlarm(this, task);
                        }
                        taskViewModel.delete(task);
                    }).create();
            dialog.show();
        });

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }
}
