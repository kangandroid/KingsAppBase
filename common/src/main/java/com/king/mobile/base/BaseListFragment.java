package com.king.mobile.base;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.util.R;
import com.king.mobile.util.ToastUtil;
import com.king.refresh.base.OnPullListener;
import com.king.refresh.base.RefreshLayout;
import com.king.refresh.normalstyle.NestRefreshLayout;

import java.util.List;

public abstract class BaseListFragment<T> extends BaseFragment {

    protected static final int TYPE_REFRESH = 0x1;
    protected static final int TYPE_LOAD_MORE = 0x2;
    protected static final int TYPE_LOAD = 0x3;
    protected static final Integer PAGE_SIZE = 10;
    Integer mPageNum = 1;

    protected RecyclerView recyclerView;
    protected NestRefreshLayout refreshLayout;
    protected boolean hasData = false;

    protected String noDataDesc = "没有数据";
    private boolean hasHeaderItem;
    protected HeaderAndFooterWrapper wrapper;
    protected BaseListAdapter<T> mAdapter;
    protected RecyclerView.LayoutManager defaultLayoutManager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.layout_list;
    }

    @Override
    protected void initView(@NonNull View view) {
        init(view);
        load();
    }

    /**
     * desc:初始化基础数据，子类可以选择实现此方法，用于初始化一些基本数据信息
     */
    protected void initBaseData() { }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && !hasData) {
            load();
        }
    }


    protected void setLayoutManager(RecyclerView recyclerView){
        defaultLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(defaultLayoutManager);
    }


    protected void init(View view) {
        refreshLayout = view.findViewById(R.id.refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setFitsSystemWindows(true);
        setupRefresh();
        setupList();
        initBaseData();
    }

    private void setupList() {
        View headerItemView = getHeaderItemView();
        hasHeaderItem = (headerItemView != null);
        recyclerView.setFocusable(false);
        setLayoutManager(recyclerView);
        mAdapter = createAdapter();
        if (mAdapter == null) {
            throw new NullPointerException("mAdapter must nut null!");
        }
        if (hasHeaderItem) {
            wrapper = new HeaderAndFooterWrapper();
            wrapper.setInnerAdapter(mAdapter);
            wrapper.addHeaderView(headerItemView);
            recyclerView.setAdapter(wrapper);
        } else {
            recyclerView.setAdapter(mAdapter);
        }
    }

    protected abstract BaseListAdapter createAdapter();

    protected View getHeaderItemView() {
        return null;
    }


    protected void load() {
        mPageNum = 1;
        if (refreshLayout != null)
            refreshLayout.showLoadingView();
        requestData(TYPE_LOAD);
    }

    protected void refresh() {
        mPageNum = 1;
        requestData(TYPE_REFRESH);
    }

    protected void loadMore() {
        mPageNum++;
        requestData(TYPE_LOAD_MORE);
    }


    protected void dealError(int type) {
        if (refreshLayout != null) {
            if (refreshLayout != null)
                refreshLayout.onLoadFinished();
            if (type == TYPE_REFRESH) {
                ToastUtil.show("刷新失败");
            } else if (type == TYPE_LOAD_MORE) {
                ToastUtil.show("加载失败");
                mPageNum--;
            } else {
                refreshLayout.showNetErrorView();
            }
        }
    }

    /**
     * 请求数据结束后onSuccess 中调用
     *
     * @param type
     * @param list
     * @param lastPage
     */
    protected void dealSuccess(int type, List<T> list, boolean lastPage) {
        if(list.size()>0){
            hasData = true;
        }
        if (type != TYPE_LOAD_MORE) {
            mAdapter.clearList();
        }
        mAdapter.addList(list);
        if (hasHeaderItem && wrapper != null) {
            wrapper.notifyDataSetChanged();
        }
        if (refreshLayout != null) {
            if (lastPage) {
                refreshLayout.onLoadAll();
            } else {
                refreshLayout.onLoadFinished();
            }
            if (type == TYPE_REFRESH) {
                if (list != null && list.size() > 0) {
                    refreshLayout.showContent();
                } else {
                    if (!hasHeaderItem) {
                        refreshLayout.showEmptyView();
                    } else {
                        refreshLayout.showContent();
                    }
                }
            } else if (type == TYPE_LOAD_MORE) {
                refreshLayout.showContent();
            } else {
                if (list != null && list.size() > 0) {
                    refreshLayout.showContent();
                } else {
                    if (!hasHeaderItem) {
                        refreshLayout.showEmptyView();
                    } else {
                        refreshLayout.showContent();
                    }
                }
            }
        }

    }


    protected abstract void requestData(int requestType);

    private OnPullListener listener = new OnPullListener() {
        @Override
        public void onRefresh(RefreshLayout listLoader) {
            refresh();
        }

        @Override
        public void onLoading(RefreshLayout listLoader) {
            loadMore();
        }
    };

    private void setupRefresh() {
        if (refreshLayout != null) {
            refreshLayout.setOnLoadingListener(listener);
            View emptyView = View.inflate(getActivity(), R.layout.common_no_data_layout, null);
            TextView viewById = (TextView) emptyView.findViewById(R.id.tv_tip);
            viewById.setText(Html.fromHtml(noDataDesc));
            refreshLayout.setEmptyView(emptyView);
            View loadingView = View.inflate(getActivity(), R.layout.common_loading_layout, null);
            refreshLayout.setLoadingView(loadingView);
            View netErrorView = View.inflate(getActivity(), R.layout.common_server_error_layout, null);
            netErrorView.setOnClickListener(v -> load());
            refreshLayout.setmNetErrorViewView(netErrorView);
        }
    }

}
