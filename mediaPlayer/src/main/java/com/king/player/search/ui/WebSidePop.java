package com.king.player.search.ui;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.widget.CommonPop;
import com.king.player.R;
import com.king.player.search.WebSiteInfo;
import com.king.player.search.WebsiteViewModel;
import com.king.player.search.adapter.WebsiteAdapter;

import java.util.List;

public class WebSidePop extends CommonPop {

    private AppCompatActivity mContext;
    private WebSiteDialog webSiteDialog;
    private WebsiteAdapter adapter;
    private LinearLayout llAddWebsite;

    public WebSidePop(AppCompatActivity context) {
        super(context);
        mContext = context;
        Application application = context.getApplication();
        WebsiteViewModel.getInstance(application)
                .getAllWebSite()
                .observe(mContext, all -> adapter.submitList(all));
        llAddWebsite.setOnClickListener(v ->
                webSiteDialog.show(mContext.getSupportFragmentManager(), WebSiteDialog.TAG));
    }

    @Override
    protected void initView(View view) {
        webSiteDialog = new WebSiteDialog();
        llAddWebsite = view.findViewById(R.id.ll_add_website);
        view.findViewById(R.id.ib_expend_dismiss).setOnClickListener(v -> dismiss());
        RecyclerView websiteList = view.findViewById(R.id.website_list);
        websiteList.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new WebsiteAdapter();
        websiteList.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pop_web_site;
    }
}
