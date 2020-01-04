package com.king.player.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.gyf.immersionbar.ImmersionBar;
import com.king.mobile.component.Callback;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.widget.CommonDialog;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.model.VideoInfo;
import com.king.player.viewmodel.VideoViewModel;

public class VideoInfoDialog extends CommonDialog {
    private VideoViewModel vvm;
    private EditText mEtName;
    private EditText mEtUrl;
    private EditText mEtDesc;
    private TitleBar titleBar;

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
        this.setCancelable(false);
        vvm = ViewModelProviders.of(this).get(VideoViewModel.class);

        titleBar = mRootView.findViewById(R.id.title_bar);
        titleBar.setTitle("视频信息")
                .immersive(this,false)
                .setTitleTextColor(R.color.white)
                .setTitleBarColorRes(R.color.colorPrimary)
                .setLeftAction(new TitleBar.Action("取消", 0, v -> dismiss()))
                .setRightAction(new TitleBar.Action("确定", 0, v -> submit()))
                .invalidate();
        mEtName = mRootView.findViewById(R.id.et_name);
        mEtUrl = mRootView.findViewById(R.id.et_url);
        mEtDesc = mRootView.findViewById(R.id.et_desc);

    }

    @Override
    protected boolean initImmersionBar() {
//        ImmersionBar.with(this).keyboardEnable(true).init();
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
        String desc = mEtDesc.getText().toString();
        if (TextUtils.isEmpty(desc)) {
            ToastUtil.show("描述不能为空");
            return;
        }
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.url = url;
        videoInfo.name = name;
        videoInfo.desc = desc;
        vvm.insert(videoInfo, new Callback() {

            @Override
            public void onResult(Object reuslt) {
                Snackbar.make(titleBar, "添加成功！", Snackbar.LENGTH_SHORT).show();
                dismiss();
            }

            @Override
            public void onError(Object reuslt) {
                Snackbar.make(titleBar, "添加失败！", Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
