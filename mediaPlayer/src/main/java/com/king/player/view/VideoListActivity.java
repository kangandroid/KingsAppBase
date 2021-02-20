package com.king.player.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.adapter.VideoListAdapter;
import com.king.player.view.fragment.VideoInfoDialog;
import com.king.player.viewmodel.VideoCollectionViewModel;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;


public class VideoListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private VideoCollectionViewModel vvm;

    @Override
    protected int getContentLayoutId() {
        return R.layout.layout_recycle_list;
    }


    @Override
    protected boolean isOverlay() {
        return false;
    }

    @Override
    protected void initView() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.RECORD_AUDIO)
                .onGranted(data -> Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show())
                .onDenied(data -> Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show());
        ViewModelProvider provider = new ViewModelProvider(this);
        vvm =  provider.get(VideoCollectionViewModel.class);
        titleBar.setLeftAction(null)
                .setTitle("视屏列表")
                .setRightAction(new TitleBar.Action(null, R.drawable.ic_add, v -> showDialog()))
                .invalidate();
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VideoListAdapter videoListAdapter = new VideoListAdapter(this);
        recyclerView.setAdapter(videoListAdapter);
        vvm.getVideoList().observe(this,(data)->{

        });
        videoListAdapter.setOnItemClickListener((video, view, index) -> play(video.url));
    }

    private static final int READ_REQUEST_CODE = 42;

    private void showDialog() {
        VideoInfoDialog.show(getSupportFragmentManager());
    }

    public void performFileSearch() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.RECORD_AUDIO)
                .onGranted(data -> {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("video/*");
                    startActivityForResult(intent, READ_REQUEST_CODE);
                })
                .onDenied(data -> Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show())
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
            }
        }
    }


    private void play(String url) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
