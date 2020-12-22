package com.king.player.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.king.mobile.base.BaseActivity;
import com.king.mobile.util.ColorUtil;
import com.king.mobile.util.ScreenAdapter;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.search.ui.SearchActivity;
import com.king.player.view.fragment.LatestFragment;
import com.king.player.view.fragment.LiveTVFragment;
import com.king.player.view.fragment.LocalVideoFragment;
import com.king.player.view.fragment.RecordManagerFragment;
import com.king.player.view.fragment.SplashFragment;
import com.king.player.view.fragment.VideoInfoDialog;
import com.king.player.viewmodel.VideoViewModel;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_PT;

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
    private TitleBar.Action actionSearch = new TitleBar.Action(null, R.drawable.ic_action_search,
            v -> goSearch());

    private void goSearch() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    private TitleBar.Action actionSetting = new TitleBar.Action(null, R.drawable.ic_settings,
            v -> {

            });
    private TitleBar.Action actionSync = new TitleBar.Action(null, R.drawable.ic_loop,
            v -> videoViewModel.loadLiveTv());

    private ViewPager vpContent;
    private List<Fragment> fragmentList;
    private TabLayout tabLayout;
    private String[] tabTexts = {"最近", "收藏", "下载", "本地"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SplashFragment().show(getSupportFragmentManager(),SplashFragment.TAG);
    }

    @Override
    protected boolean isOverlay() {
        return true;
    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        tabLayout = new TabLayout(this);
        tabLayout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#000000"));
        tabLayout.setInlineLabel(true);
        tabLayout.setUnboundedRipple(true);
        tabLayout.setTabIndicatorFullWidth(false);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);
        titleBar.setTitleView(tabLayout)
                .immersive(this, true)
                .setLeftAction(actionSearch)
                .setRightAction(actionAdd)
                .invalidate();
    }

    @Override
    protected void initView() {
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        createFragment();
        vpContent = findViewById(R.id.vp_content);
        FragmentManager fm = getSupportFragmentManager();
        vpContent.setAdapter(new HomeFragmentPagerAdapter(fm, fragmentList));
        vpContent.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(vpContent);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView view = (TextView) tab.getCustomView();
                view.setTextColor(ColorUtil.getColor("#111111"));
                view.getPaint().setFakeBoldText(true);
                view.setTextSize(COMPLEX_UNIT_PT,40);
                view.setWidth((int) ScreenAdapter.pt2px(HomeActivity.this,110));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView view = (TextView) tab.getCustomView();
                view.setTextColor(ColorUtil.getColor("#666666"));
                view.getPaint().setFakeBoldText(false);
                view.setWidth((int) ScreenAdapter.pt2px(HomeActivity.this,72));
                view.setTextSize(COMPLEX_UNIT_PT,36);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        setTabs();
    }

    private void createFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new LatestFragment());
        fragmentList.add(new LiveTVFragment());
        fragmentList.add(new RecordManagerFragment());
        fragmentList.add(new LocalVideoFragment());
    }

    private void setTabs() {
        for (int index = 0; index < tabTexts.length; index++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(index);
            TextView textView = new TextView(this);
            if (tabAt.isSelected()) {
                textView.setTextSize(COMPLEX_UNIT_PT,40);
                textView.getPaint().setFakeBoldText(true);
                textView.setTextColor(ColorUtil.getColor("#111111"));
                textView.setWidth((int) ScreenAdapter.pt2px(HomeActivity.this,110));
            } else {
                textView.setTextSize(COMPLEX_UNIT_PT,36);
                textView.setTextColor(ColorUtil.getColor("#666666"));
                textView.setWidth((int) ScreenAdapter.pt2px(HomeActivity.this,72));
            }
            textView.setText(tabTexts[index]);
            tabAt.setCustomView(textView);
        }
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    long previousBackPress = 0;

    @Override
    public void onBackPressed() {
        long l = System.currentTimeMillis();
        if (l - previousBackPress < 1500) {
            finish();
        } else {
            ToastUtil.show("再按一次退出");
            previousBackPress = l;
        }

    }
}
