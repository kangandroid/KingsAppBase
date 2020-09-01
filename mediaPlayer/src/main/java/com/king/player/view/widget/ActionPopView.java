package com.king.player.view.widget;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.king.mobile.downloadlib.DownloadManager;
import com.king.mobile.util.ColorUtil;
import com.king.mobile.util.Executor;
import com.king.mobile.util.Loker;
import com.king.mobile.widget.CommonDialog;
import com.king.mobile.widget.CommonPop;
import com.king.player.App;
import com.king.player.R;
import com.king.player.model.VideoInfo;
import com.king.player.viewmodel.VideoViewModel;

public class ActionPopView extends CommonPop {
    private VideoViewModel videoViewModel;
    VideoInfo videoInfo;
    private FragmentManager fragmentManager;
    private Context mContext;
    private View editContent;
    private EditText etName;
    private EditText etDesc;
    private TextView deleteTip;

    private CommonDialog.OnConfirmListener deleteVideo = dialog -> {
        videoViewModel.delete(videoInfo);
        dialog.dismiss();
    };

    private CommonDialog.OnConfirmListener updateVideoInfo = dialog -> {
        videoInfo.name = etName.getText().toString();
        videoInfo.desc = etDesc.getText().toString();
        videoViewModel.updateVideoInfo(videoInfo);
        dialog.dismiss();
    };

    public ActionPopView(Context context, VideoInfo videoInfo) {
        super(context);
        mContext = App.mContext;
        this.videoInfo = videoInfo;
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            videoViewModel = ViewModelProviders.of(activity).get(VideoViewModel.class);
            fragmentManager = activity.getSupportFragmentManager();
        } else {
            throw new RuntimeException("context is not activity");
        }

        editContent = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit, null);
        etName = editContent.findViewById(R.id.et_name);
        etDesc = editContent.findViewById(R.id.et_desc);
        etName.setText(videoInfo.name);
        etDesc.setText(videoInfo.desc);

        deleteTip = new TextView(mContext);
        deleteTip.setTextColor(ColorUtil.getColor(mContext, R.color.textBlack));
        deleteTip.setTextSize(14);
        deleteTip.setText(Html.fromHtml(String.format("<html>视频<font color='#f57c00'><b>%s</b></font>将从列表中移除！</html>",videoInfo.name)));
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.action_edit).setOnClickListener(v -> showEditDialog());
        view.findViewById(R.id.action_delete).setOnClickListener(v -> showDeleteDialog());
        view.findViewById(R.id.action_download).setOnClickListener(v -> Executor.getInstance().execute(this::download));
    }

    private void download() {
        DownloadManager instance = DownloadManager.getInstance(mContext);
        instance.download(videoInfo.url);
    }


    private void showDeleteDialog() {
        dismiss();
        new CommonDialog.Builder()
                .setTitle("视频信息")
                .setContent(deleteTip)
                .onConfirm(deleteVideo)
                .build()
                .show(fragmentManager, "deleteTip");
    }

    private void showEditDialog() {
        dismiss();
        new CommonDialog.Builder()
                .setTitle("视频信息")
                .setContent(editContent)
                .onConfirm(updateVideoInfo)
                .build()
                .show(fragmentManager, "editContent");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_video_option;
    }
}
