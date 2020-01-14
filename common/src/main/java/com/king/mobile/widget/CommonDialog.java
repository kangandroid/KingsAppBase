package com.king.mobile.widget;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.king.mobile.util.R;

public class CommonDialog extends BaseDialog {

    private String title;
    private View content;
    private OnConfirmListener confirmListener;

    private TextView tvTitle;
    private TextView cancel;
    private TextView confirm;
    private FrameLayout flContent;

    @Override
    protected int setLayoutId() {
        return R.layout.dialog_common_tip;
    }

    public CommonDialog() {
        dialogPosition = DIALOG_NORMAL;
    }

    @Override
    protected void initView(View mRootView) {
        tvTitle = mRootView.findViewById(R.id.title);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        flContent = mRootView.findViewById(R.id.content);
        flContent.addView(content);
        cancel = mRootView.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> dismiss());
        confirm = mRootView.findViewById(R.id.confirm);
        confirm.setOnClickListener(v -> {
            if (confirmListener != null) {
                confirmListener.onConfirm(this);
            } else {
                dismiss();
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContentView(View view) {
        content = view;
    }

    public void onConfirm(OnConfirmListener listener) {
        confirmListener = listener;
    }

    public interface OnConfirmListener {
        void onConfirm(CommonDialog dialog);
    }

    public static class Builder {
        private String title;
        private View content;
        private OnConfirmListener onConfirm;
        private DialogInterface onCancel;

        public CommonDialog build() {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.setTitle(title);
            commonDialog.setContentView(content);
            commonDialog.onCancel(onCancel);
            commonDialog.onConfirm(onConfirm);
            return commonDialog;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(View content) {
            this.content = content;
            return this;
        }


        public Builder onConfirm(OnConfirmListener onConfirm) {
            this.onConfirm = onConfirm;
            return this;
        }

        public Builder onCancel(DialogInterface onCancel) {
            this.onCancel = onCancel;
            return this;
        }
    }


}
