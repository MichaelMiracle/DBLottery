package com.miracle.base.network;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.util.ToastUtil;

import java.util.List;

import retrofit2.Call;

/**
 * Created by NaOH on 2018/8/3 15:11 (星期五).
 */
public abstract class ZPageLoadCallback<T> extends ZCallback<T> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerViewAdapter mAdapter;
    private int page = 1;
    private int pageSize = 20;
    private boolean isLoadMore;


    public ZPageLoadCallback(RecyclerViewAdapter adapter, RecyclerView recyclerView) {
        adapter.setOnLoadMoreListener(this, recyclerView);
        mAdapter = adapter;
    }

    public ZPageLoadCallback(RecyclerViewAdapter adapter, RecyclerView recyclerView, INetStatusUI netStatusUI) {
        this(adapter, recyclerView);
        setNetStatusUI(netStatusUI);
    }


    /**
     * 配置SwipeRefreshLayout,刷新自动请求数据
     *
     * @param swipeRefreshLayout
     */
    public void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 自定义每页请求的数量
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    protected void onSuccess(T data) {
        ZResponse zResponse = ((ZResponse) data);
        //非200即0
        if (zResponse.getCode() == 200) {
            page++;
            if (isLoadMore) {
                mAdapter.addData((List) zResponse.getData());
            } else {
                mAdapter.setNewData((List) zResponse.getData());
            }
            if (mAdapter.getData().size() == zResponse.getTotal()) {
                mAdapter.loadMoreEnd();
            } else {
                mAdapter.loadMoreComplete();
            }
        } else {
            mAdapter.loadMoreEnd();
        }
    }


    @Override
    public void onFailure(Call call, Throwable t) {
        ToastUtil.toast(t.getMessage());
        onFinish(call);
        mAdapter.loadMoreFail();

        checkBaseActUIStatus();
        checkINetUIStatus();
    }

    private void checkINetUIStatus() {
        if (mNetStatusUI == null) return;
        if (mAdapter.getData().isEmpty()) {
            mNetStatusUI.showError();
        } else {
            mNetStatusUI.showContent();
        }
    }

    private void checkBaseActUIStatus() {
        if (mBaseActivity == null) return;
        if (mAdapter.getData().isEmpty()) {
            mBaseActivity.showError();
        } else {
            mBaseActivity.showContent();
        }
    }

    @Override
    public void handlePlaceHolder(int code) {
        if (mBaseActivity != null){
            if (code == 200) {
                mBaseActivity.showContent();
            } else {
                if (mAdapter.getData().isEmpty()) {
                    mBaseActivity.showEmpty();
                } else {
                    mBaseActivity.showContent();
                }
            }
        }

        if (mNetStatusUI != null) {
            if (code == 200) {
                mNetStatusUI.showContent();
            } else {
                if (mAdapter.getData().isEmpty()) {
                    mNetStatusUI.showEmpty();
                } else {
                    mNetStatusUI.showContent();
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout != null && !mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(true);
//        if (!NetStateUtils.isNetworkConnected(App.getApp())) {
//            ToastUtil.toast(App.getApp(), CommonUtils.getString(R.string.no_net));
//            mSwipeRefreshLayout.setRefreshing(false);
//            onFailure(null, new Throwable("当前无网络"));
//            return;
//        }
        isLoadMore = false;
        page = 1;
        requestAction(page, pageSize);
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        requestAction(page, pageSize);
    }

    public abstract void requestAction(int page, int pageSize);
}
