package com.king.mobile.wakap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.wakap.model.Task;
import com.king.mobile.widget.TitleBar;

import java.util.List;

public class MainActivity extends BaseActivity {
    private TaskViewModel taskViewModel;
    private RecyclerView list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        ImageButton btnAddAlarm = findViewById(R.id.add_alarm);
        btnAddAlarm.setOnClickListener(v -> startActivity(new Intent(this, TaskCreateActivity.class)));
        list = findViewById(R.id.recycler_view);
        list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        TaskListAdapter adapter = new TaskListAdapter(this);
        list.setAdapter(adapter);
        taskViewModel.getTasks().observe(this, (List<Task> data) -> adapter.setData(data));
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
