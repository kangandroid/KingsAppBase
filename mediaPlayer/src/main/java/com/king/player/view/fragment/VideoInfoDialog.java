package com.king.player.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.widget.BaseDialog;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.video.VideoInfo;
import com.king.player.video.VideoType;
import com.king.player.viewmodel.VideoViewModel;

public class VideoInfoDialog extends BaseDialog {
    private VideoViewModel vvm;
    private EditText mEtName;
    private EditText mEtUrl;
    private TitleBar titleBar;
    private Spinner mType;
    private View mRootView;

    private VideoInfoDialog() {
    }

    private static VideoInfoDialog getInstance() {
        return VideoInfoDialog.InstanceHolder.instance;
    }

    public static void show(FragmentManager fg) {
        VideoInfoDialog instance = getInstance();
        instance.show(fg, "video");
    }


    private static class InstanceHolder {
        static VideoInfoDialog instance = new VideoInfoDialog();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.dialog_video_info;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogPosition = DIALOG_POSITION_TOP;
    }

    @Override
    protected void initView(View mRootView) {
        this.mRootView = mRootView;
        setCancelable(true);
        vvm = ViewModelProviders.of(this).get(VideoViewModel.class);
        titleBar = mRootView.findViewById(R.id.title_bar);
        titleBar.setTitle("视频信息")
                .immersive(this, false)
                .setTitleTextColor(R.color.white)
                .setTitleBarColorRes(R.color.Primary)
                .setLeftAction(new TitleBar.Action("取消", 0, v -> dismiss()))
                .setRightAction(new TitleBar.Action("确定", 0, v -> submit()))
                .invalidate();
        mEtName = mRootView.findViewById(R.id.et_name);
        mEtUrl = mRootView.findViewById(R.id.et_url);
        mType = mRootView.findViewById(R.id.spinner);
        String[] allType = VideoType.getTypeArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, R.layout.item_type, allType);
        adapter.setDropDownViewResource(R.layout.item_drop_down);
        mType.setAdapter(adapter);

    }

    @Override
    protected boolean initImmersionBar() {
        return true;
    }

    private void submit() {
        String name = mEtName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.show("名称不能为空");
            return;
        }
        String url = mEtUrl.getText().toString();
        if (TextUtils.isEmpty(url)) {
            ToastUtil.show("URL不能为空");
            return;
        }
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.url = url;
        videoInfo.name = name;
        videoInfo.createTime = System.currentTimeMillis() / 1000;
        vvm.insert(videoInfo).subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable e) {
        Snackbar.make(mRootView, "创建视频出错", Snackbar.LENGTH_SHORT).show();
    }

    private void onSuccess(Boolean next) {
        ToastUtil.show(next ? "创建成功" : "地址已存在");
        mEtName.setText("");
        mEtUrl.setText("");
        dismiss();
    }
}
