package com.king.mobile.testapp.daemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Printer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.king.mobile.testapp.R;

@Route(path = "/daemon/activity")
public class KeepAliveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        print("onCreate");
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        view.setLayoutParams(new ViewGroup.LayoutParams(1, 5));
        Window window = getWindow();
        window.setGravity(Gravity.END | Gravity.CENTER);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 1;
        attributes.height = 5;
        window.setAttributes(attributes);
        ScreenManager.getInstance().setActivity(this);
        bindService();
    }
    void bindService() {
        Intent service = new Intent(this, TestService.class);
        boolean b = bindService(service, sc, BIND_ALLOW_OOM_MANAGEMENT);
        // false 表示没有绑定超过 有可能没找到相应的服务或没有权限绑定，
        // true表示绑定成功，需要调用unbindService 来解除绑定
        print("b="+b);
        // unbindService(sc);
    }



    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            print("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            print("onServiceConnected");
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        print("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        print("onPause");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        print("onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        print("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        print("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        print("onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        print("onBackPressed");

    }

    void print(String s) {
        System.out.println("KeepAliveActivity-------------------:" + s);
    }

}