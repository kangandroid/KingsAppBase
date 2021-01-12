package com.king.mobile.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Printer;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.king.mobile.testapp.fragment.ItemFragment;
import com.king.mobile.testapp.fragment.TestFragment;
import com.king.mobile.testapp.receiver.MessageReadReceiver;
import com.king.mobile.testapp.daemon.TestService;
import com.king.mobile.testapp.utils.LogUtil;
import com.king.mobile.testapp.utils.StartMonitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

//@AndroidEntryPoint
@Route(path = "/test/main")
public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    private TestFragment testFragment;
    private TestFragment testFragment1;
    private TestFragment testFragment2;
    private TestFragment testFragment3;
    private ItemFragment itemFragment;
    private ViewPager viewPager;
    private boolean connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.print("MainActivity", "onCreate");
//        itemFragment = new ItemFragment();
//        loopPrint();
//        requestWindowFeature(FEATURE_CUSTOM_TITLE);
        testFragment = new TestFragment();
        testFragment1 = new TestFragment("testFragment1");
        testFragment2 = new TestFragment("testFragment2");
        testFragment3 = new TestFragment("testFragment3");
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        findViewById(R.id.button).setOnClickListener(v -> add());
        findViewById(R.id.button_1).setOnClickListener(v -> bindRemoteService());
        findViewById(R.id.button_2).setOnClickListener(v -> getRemoteInfo());
        findViewById(R.id.button_3).setOnClickListener(v -> startMain());
        findViewById(R.id.button_4).setOnClickListener(v -> remove());
        findViewById(R.id.button_6).setOnClickListener(v -> show());
        findViewById(R.id.button_5).setOnClickListener(v -> hide());
        viewPager = findViewById(R.id.view_pager);
//        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageMargin(200);
        viewPager.setPageMarginDrawable(R.drawable.ic_launcher_background);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return testFragment1;
                    case 1:
                        return testFragment2;
                    case 2:
                        return testFragment3;
                }
                return itemFragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
//        showFragment();
    }

    private void anr() {
        try {
            Thread.sleep(8*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getRemoteInfo() {
        if (connected) {
            try {
                String answer = anInterface.getAnswer("你好,------你好");
                Toast.makeText(this, answer, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void remove() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(testFragment).commit();

//        FrameLayout frameLayout = new FrameLayout(this);
//        frameLayout.setAnimationCacheEnabled(true);
//        frameLayout.setDrawingCacheEnabled(true);
//        frameLayout.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
//        frameLayout.setAlwaysDrawnWithCacheEnabled(true);
//        frameLayout.isHardwareAccelerated();
    }
    private void show() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(testFragment).commit();
    }
    private void hide() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(testFragment).commit();
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

    void bindRemoteService() {
        Intent service = new Intent(this, TestService.class);
//        startService(service);
        bindService(service, sc, BIND_AUTO_CREATE);//        stopService(service);
    }

    private AidlTestInterface anInterface;


    private IBinder mService;
    private ServiceConnection sc = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            print("onServiceConnected name = " + name.getClassName());
            anInterface = AidlTestInterface.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            print("onServiceDisconnected");
        }
    };

    void loopPrint() {
        Looper mainLooper = Looper.getMainLooper();
        Looper.myQueue().addIdleHandler(() -> {
            print("Looper.myQueue onIdle");
            return false;
        });
        Printer printer = this::print;
        mainLooper.setMessageLogging(printer);

    }

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

    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim,exitAnim);
        print("overridePendingTransition");

    }



    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        print("onConfigurationChanged");
    }

    void throwException() {
        try {
            File file = new File("/sss");
            InputStream inputStream = new FileInputStream(file);
            inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private void add() {
        print("showFragment");

        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
//            @Override
//            public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
//                super.onFragmentPreAttached(fm, f, context);
//            }
//
//            @Override
//            public void onFragmentAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
//                super.onFragmentAttached(fm, f, context);
//            }
//
//            @Override
//            public void onFragmentPreCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
//                super.onFragmentPreCreated(fm, f, savedInstanceState);
//            }
//
//            @Override
//            public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
//                super.onFragmentCreated(fm, f, savedInstanceState);
//            }
//
//            @Override
//            public void onFragmentActivityCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
//                super.onFragmentActivityCreated(fm, f, savedInstanceState);
//            }
//
//            @Override
//            public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
//                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
//            }
//
//            @Override
//            public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentStarted(fm, f);
//            }
//
//            @Override
//            public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentResumed(fm, f);
//            }
//
//            @Override
//            public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentPaused(fm, f);
//            }
//
//            @Override
//            public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentStopped(fm, f);
//            }
//
//            @Override
//            public void onFragmentSaveInstanceState(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Bundle outState) {
//                super.onFragmentSaveInstanceState(fm, f, outState);
//            }
//
//            @Override
//            public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentViewDestroyed(fm, f);
//            }
//
//            @Override
//            public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentDestroyed(fm, f);
//            }
//
//            @Override
//            public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentDetached(fm, f);
//            }
//        },false);
        fragmentManager.beginTransaction().add(R.id.container, testFragment, "TestFragment").commit();
    }

    private void unbind() {
        unbindService(sc);
        print("unbind");
    }

    @Override
    protected void onResume() {
        StartMonitor.getInstance().startCompleted();
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
        sendOrderedBroadcast(intent, "");
        registerReceiver(receiver, filter);

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
//        unbindService(sc);
    }

    void startMain() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_SCREEN_ON);
//        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        ScreenBroadcastReceiver receiver = new ScreenBroadcastReceiver();
//        registerReceiver(receiver, filter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        print("onRestart");
    }

    void print(String s) {
        System.out.println("MainActivity-------------------:" + s);
    }
}
