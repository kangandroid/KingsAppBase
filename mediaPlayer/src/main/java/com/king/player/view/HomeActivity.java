package com.king.player.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.king.mobile.base.BaseActivity;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.view.fragment.LocalVideoFragment;
import com.king.player.view.fragment.RecordManagerFragment;
import com.king.player.view.fragment.RemoteVideoFragment;
import com.king.player.view.fragment.SplashFragment;
import com.king.player.view.fragment.VideoInfoDialog;
import com.king.player.viewmodel.VideoViewModel;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private VideoViewModel videoViewModel;
    private TitleBar.Action actionScan = new TitleBar.Action(null, R.drawable.ic_loop,
            v -> AndPermission.with(this)
                    .runtime()
                    .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                    .onGranted(data -> videoViewModel.loadLocalVideo())
                    .onDenied(data -> Toast.makeText(this, getString(R.string.permission_deny), Toast.LENGTH_SHORT).show())
                    .start());

    private TitleBar.Action actionAdd = new TitleBar.Action(null, R.drawable.ic_action_add,
            v -> VideoInfoDialog.show(getSupportFragmentManager()));
    private TitleBar.Action actionSetting = new TitleBar.Action(null, R.drawable.ic_settings,
            v -> {

            });

    private ViewPager vpContent;
    private BottomNavigationView bnmv;
    private List<Fragment> fragmentList;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = menuItem -> {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.local_video:
                vpContent.setCurrentItem(0, true);
                titleBar.setRightAction(actionScan)
                        .setTitle("本地视频")
                        .setLeftAction(null)
                        .invalidate();
                break;
            case R.id.remote_video:
                vpContent.setCurrentItem(1, true);
                titleBar.setRightAction(actionAdd)
                        .setLeftAction(null)
                        .setTitle("网络视频")
                        .invalidate();
                break;
            case R.id.record_manage:
                vpContent.setCurrentItem(2, true);
                titleBar.setRightAction(null)
                        .setLeftAction(actionSetting)
                        .setTitle("视频管理")
                        .invalidate();
                break;
        }
        return true;
    };
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    bnmv.setSelectedItemId(R.id.local_video);
                    break;
                case 1:
                    bnmv.setSelectedItemId(R.id.remote_video);
                    break;
                case 2:
                    bnmv.setSelectedItemId(R.id.record_manage);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashFragment.show(getSupportFragmentManager());
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitleBarColorRes(R.color.title_bg)
                .setTitle(R.string.app_name)
                .setTitleTextColor(R.color.title_text)
                .setLeftAction(null)
                .immersive(this, true)
                .setRightAction(actionScan)
                .invalidate();
    }

    @Override
    protected void initView() {
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        fragmentList = new ArrayList<>();
        fragmentList.add(new LocalVideoFragment());
        fragmentList.add(new RemoteVideoFragment());
        fragmentList.add(new RecordManagerFragment());
        vpContent = findViewById(R.id.vp_content);
        FragmentManager fm = getSupportFragmentManager();
        vpContent.setAdapter(new HomeFragmentPagerAdapter(fm, fragmentList));
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(mOnPageChangeListener);
        bnmv = findViewById(R.id.bottom_nav_view);
        bnmv.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    long previousBackpress = 0;

    @Override
    public void onBackPressed() {
        long l = System.currentTimeMillis();
        if (l - previousBackpress < 1500) {
            finish();
        } else {
            ToastUtil.show("再按一次退出");
            previousBackpress = l;
        }

    }
}
