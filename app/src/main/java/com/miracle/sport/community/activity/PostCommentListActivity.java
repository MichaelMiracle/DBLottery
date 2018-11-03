package com.miracle.sport.community.activity;

import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.databinding.SwipeRecyclerBinding;

/**
 * Created by Michael on 2018/11/3 21:07 (星期六)
 */
public class PostCommentListActivity extends BaseActivity<SwipeRecyclerBinding> {

    @Override
    public int getLayout() {
        return R.layout.swipe_recycler;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void loadData() {

    }
    private void reqCommentList() {
//        ZClient.getService(SportService.class).getPostCommentList(id).enqueue(new ZCallback<ZResponse<List<PostCommentBean>>>() {
//            @Override
//            public void onSuccess(ZResponse<List<PostCommentBean>> data) {
//                pAdapter.setNewData(data.getData());
//            }
//        });
    }
    @Override
    public void onClick(View v) {

    }
}
