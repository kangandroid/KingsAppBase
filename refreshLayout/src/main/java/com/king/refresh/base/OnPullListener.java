package com.king.refresh.base;

/**
 * <br>功能描述：分页加载的回调，包括下拉刷新
 */
public interface OnPullListener {

    void onRefresh(RefreshLayout listLoader);

    void onLoading(RefreshLayout listLoader);
}
