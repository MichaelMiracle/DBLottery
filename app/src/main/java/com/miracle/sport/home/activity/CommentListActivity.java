package com.miracle.sport.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.base.Constant;
import com.miracle.base.network.RequestUtil;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZPageLoadCallback;
import com.miracle.base.network.ZResponse;
import com.miracle.databinding.SwipeRecyclerBinding;
import com.miracle.michael.common.bean.ArticleCommentBean;
import com.miracle.michael.common.bean.ArticleDetailBean;
import com.miracle.sport.SportService;
import com.miracle.sport.home.adapter.ArticleListAdapter;
import com.miracle.sport.home.bean.Football;
import com.miracle.sport.me.adapter.CollectionsListAdapter;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;

public class CommentListActivity extends BaseActivity<SwipeRecyclerBinding> {

    private ArticleListAdapter mAdapter;
    private ZPageLoadCallback callBack;
    private ArticleDetailBean articleDetailBean;

    @Override
    public int getLayout() {
        return R.layout.swipe_recycler;
    }

    @Override
    public void initView() {
        setTitle("评论列表");
        articleDetailBean = (ArticleDetailBean) getIntent().getSerializableExtra(Constant.COMMENT_LIST);
        binding.recyclerView.setAdapter(mAdapter = new ArticleListAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        showLoadingDialog();
        initCallback();
    }

    private void initCallback() {
        callBack = new ZPageLoadCallback<ZResponse<List<ArticleCommentBean>>>(mAdapter, binding.recyclerView) {
            @Override
            public void requestAction(int page, int limit) {
//                ZClient.getService(SportService.class).getMycollections(page,limit).enqueue(callBack);
                RequestUtil.cacheUpdate(ZClient.getService(SportService.class).getCommetList(articleDetailBean.getId(),page,limit),callBack);

            }
        };
        callBack.setDialog(loadingDialog);
        callBack.setBaseActivity(this);
        callBack.initSwipeRefreshLayout(binding.swipeRefreshLayout);
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void initListener() {
    }

    @Override
    public void loadData() {
        mAdapter.setNewData(null);
        callBack.onRefresh();
    }

    @Override
    public void onClick(View v) {

    }
}
