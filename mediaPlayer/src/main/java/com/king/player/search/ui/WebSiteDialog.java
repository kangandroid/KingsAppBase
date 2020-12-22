package com.king.player.search.ui;

import android.app.Application;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.king.mobile.util.ToastUtil;
import com.king.mobile.widget.BaseDialog;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.search.WebSiteInfo;
import com.king.player.search.WebsiteViewModel;
import com.king.player.util.SimpleTextWatcher;
import com.king.player.view.widget.TextInputView;

public class WebSiteDialog extends BaseDialog {
    public static final String TAG = "WebSiteDialog";
    private WebSiteInfo webSiteInfo;
    private WebsiteViewModel viewModel;


    public WebSiteDialog(WebSiteInfo webSiteInfo) {
        this.webSiteInfo = webSiteInfo;
    }

    public WebSiteDialog() {
        this.webSiteInfo = new WebSiteInfo();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.dialog_web_site;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogPosition = DIALOG_POSITION_TOP;
    }


    @Override
    protected void initView(View mRootView) {
        viewModel = new ViewModelProvider(this).get(WebsiteViewModel.class);
        setCancelable(true);
        TitleBar titleBar = mRootView.findViewById(R.id.title_bar);
        titleBar.setTitle(webSiteInfo.id == 0 ? "添加常用网址" : "修改网址信息")
                .immersive(this, false)
                .setTitleTextColor(R.color.white)
                .setTitleBarColorRes(R.color.Primary)
                .setLeftAction(new TitleBar.Action("取消", 0, v -> dismiss()))
                .setRightAction(new TitleBar.Action("保存", 0, v -> submit()))
                .invalidate();
        TextInputView tivTitle = mRootView.findViewById(R.id.tiv_title);
        tivTitle.setContent(webSiteInfo.title);
        tivTitle.addTextWatcher(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                webSiteInfo.title = s.toString();
            }
        });
        TextInputView tivUrl = mRootView.findViewById(R.id.tiv_url);
        tivUrl.setContent(webSiteInfo.url);
        tivUrl.addTextWatcher(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                webSiteInfo.url = s.toString();
            }
        });

    }

    private void submit() {
        if (TextUtils.isEmpty(webSiteInfo.title)) {
            ToastUtil.show("请输入网站名称！");
            return;
        }
        if (TextUtils.isEmpty(webSiteInfo.url)) {
            ToastUtil.show("请输入网址！");
            return;
        }
        if(webSiteInfo.id == 0){
            viewModel.add(webSiteInfo, l -> dismiss(),
                    throwable -> ToastUtil.show("保存失败稍后重试！"));
        }else {
            viewModel.update(webSiteInfo, l -> dismiss(),
                    throwable -> ToastUtil.show("保存失败稍后重试！"));
        }

    }

    @Override
    protected boolean initImmersionBar() {
        return true;
    }
}
