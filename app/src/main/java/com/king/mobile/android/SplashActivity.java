package com.king.mobile.android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    private volatile int waitSecond = 2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); // 全屏
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> startMainActivity());
        handler = new Handler(msg -> {
            if (msg.what == 1) {
                waitSecond--;
                if (waitSecond == 0) {
                    startMainActivity();
                    msg.getTarget().removeCallbacksAndMessages(null);
                } else {
                    btn.setText(String.format("%d s", waitSecond));
                    msg.getTarget().sendEmptyMessageDelayed(1, 1000);
                }
            }
            return false;
        });
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    private void startMainActivity(){
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        handler = null;
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
