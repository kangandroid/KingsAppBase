package com.king.player.view.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.king.mobile.util.R;
import com.king.mobile.widget.BaseDialog;

public class SplashFragment extends BaseDialog {
    public static final String TAG = "SplashFragment";
    private Handler handler;
    private int waitSecond = 3;

    public SplashFragment() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.layout_splash;
    }


    @Override
    protected void initView(View mRootView) {
        mRootView.findViewById(R.id.tv_skip).setOnClickListener(v -> this.dismiss());
        handler = new Handler(msg -> {
            if (msg.what == 1) {
                waitSecond--;
                Log.d("KK", String.format("剩余%dS", waitSecond));
                if (waitSecond < 0) {
                    msg.getTarget().removeCallbacksAndMessages(null);
                    dismiss();
                } else {
                    msg.getTarget().sendEmptyMessageDelayed(1, 1000);
                }
                return true;
            } else {
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }
}
