package com.king.mobile.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static android.view.Window.FEATURE_CUSTOM_TITLE;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    TestAdapter adapter;
    private FrameLayout container;
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        print("onCreate");
        requestWindowFeature(FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        findViewById(R.id.button).setOnClickListener(v -> throwException());
        container.setOnClickListener(v -> showDialog());
    }

    private void hide() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.hide(fragment).commit();
        fragmentTransaction.remove(fragment).commit();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        print("onRestoreInstanceState");
        Application application = getApplication();
        Context applicationContext = getApplicationContext();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        print("onSaveInstanceState");
    }

    void startService() {
        Intent service = new Intent(this, TestService.class);
        startService(service);

        stopService(service);
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /*          {@link #BIND_AUTO_CREATE},
     *          {@link #BIND_DEBUG_UNBIND},
     *          {@link #BIND_NOT_FOREGROUND},
     *          {@link #BIND_ABOVE_CLIENT},
     *          {@link #BIND_ALLOW_OOM_MANAGEMENT},
     *          {@link #BIND_WAIVE_PRIORITY}.
     *          {@link #BIND_IMPORTANT},
     *          {@link #BIND_ADJUST_WITH_ACTIVITY}.
     */
    void bindService() {
        Intent service = new Intent(this, TestService.class);
        boolean b = bindService(service, sc, BIND_AUTO_CREATE);
        // false 表示没有绑定超过 有可能没找到相应的服务或没有权限绑定，
        // true表示绑定成功，需要调用unbindService 来解除绑定

        // unbindService(sc);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        print("onConfigurationChanged");
    }

    void throwException() {
        throw new RuntimeException();
    }

    private void showFragment() {
        print("showFragment");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = new TestFragment();
        fragmentManager.beginTransaction().add(R.id.container, fragment, "TestFragment").commit();
    }

    private void showDialog() {
        print("showDialog");
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("Dialog").setView(new EditText(this)).show();
    }

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

    public void sendBroadcast() {
        MessageReadReceiver receiver = new MessageReadReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("");
        Intent intent = new Intent("");
        sendBroadcast(intent);
        sendOrderedBroadcast(intent,"");
        registerReceiver(receiver,filter);


        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.sendBroadcast(intent);
        filter.addAction("");
        instance.registerReceiver(receiver, filter);
        instance.unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        print("onDestroy");
    }

    void print(String s) {
        System.out.println("MainActivity-------------------:" + s);
    }
}
