package com.king.mobile.widget;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.gyf.immersionbar.ImmersionBar;
import com.king.mobile.util.R;

import java.util.Objects;

public class SplashFragment extends DialogFragment {

    private Handler handler;
    private int waitSecond ;

    private SplashFragment() { }

    private static SplashFragment getInstance() {
        return InstanceHolder.instance;
    }

    public static void show(FragmentManager fg) {
        SplashFragment instance = getInstance();
        instance.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
        instance.show(fg, "Splash");
    }

    public static void hide() {
        getInstance().dismiss();
    }


    private static class InstanceHolder {
        private static SplashFragment instance = new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_splash, null);
    }

    @Override
    public void onResume() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .init();
        WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        super.onResume();
        waitSecond = 5;
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler = new Handler(msg -> {
            if (msg.what == 1) {
                waitSecond--;
                if (waitSecond == 0) {
                    dismiss();
                    msg.getTarget().removeCallbacksAndMessages(null);
                } else {
                    msg.getTarget().sendEmptyMessageDelayed(1, 1000);
                }
            }
            return false;
        });
    }
    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }
}
