package com.king.mobile.wakap;

import android.content.Intent;

import com.king.mobile.base.BaseListAdapter;
import com.king.mobile.base.BaseListFragment;
import com.king.mobile.wakap.model.AppInfo;
import com.king.mobile.wakap.util.PackageUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApplicationFragment extends BaseListFragment<AppInfo> {

    @Override
    protected BaseListAdapter<AppInfo> createAdapter() {
        AppInfoAdapter appInfoAdapter = new AppInfoAdapter(getContext());
        appInfoAdapter.setOnItemClickListener((appInfo, view, position) -> {
            Intent data = new Intent();
            data.putExtra("packageName",appInfo.packageName);
            getActivity().setResult(100, data);
            getActivity().finish();
        });
        return appInfoAdapter;
    }

    @Override
    protected void requestData(int requestType) {
        Observable.just("appInfo").map(a -> PackageUtils.getAllInstallPages(getContext()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((List<AppInfo> appInfoList) -> dealSuccess(TYPE_LOAD, appInfoList, true));

    }
}
