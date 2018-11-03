package com.miracle.sport.onetwo.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZPageLoadCallback;
import com.miracle.base.network.ZResponse;
import com.miracle.databinding.ActivityLotteryDetailBinding;
import com.miracle.sport.onetwo.adapter.LotteryCategoryListAdapter;
import com.miracle.sport.onetwo.netbean.CPServer;
import com.miracle.sport.onetwo.netbean.LotteryCatListItem;
import com.miracle.sport.onetwo.netbean.LotteryCatTitleItem;

import java.util.List;

import retrofit2.Call;

public class LotteryListFragment extends BaseFragment<ActivityLotteryDetailBinding> {
    boolean isLoadSingle = false;
    LotteryCatTitleItem lotteryCatData;
    LotteryCategoryListAdapter mAdapter;
    ZPageLoadCallback<ZResponse<List<LotteryCatListItem>>> callback;


    public void setShowSingle(boolean isLoadSingle){
        this.isLoadSingle = isLoadSingle;
        if(mAdapter != null)
            mAdapter.setShowSingle(isLoadSingle);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_lottery_detail;
    }

    @Override
    public void initView() {
        mAdapter = new LotteryCategoryListAdapter(mContext);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public LotteryCatTitleItem getLotteryCatData() {
        return lotteryCatData;
    }

    public void setLotteryCatData(LotteryCatTitleItem lotteryCatData) {
        this.lotteryCatData = lotteryCatData;
        mAdapter.setShowSingle(isLoadSingle);
        if(isLoadSingle){
            binding.swipeRefreshLayout.setSize(0);
        }

//        setTitle("" + lotteryCatData.getName());
        final int id = lotteryCatData.getId();
        callback = new ZPageLoadCallback<ZResponse<List<LotteryCatListItem>>>(mAdapter, binding.recyclerView, this) {
            @Override
            public void requestAction(int page, int limit) {
                if(isLoadSingle)
                    ZClient.getService(CPServer.class).lotteryCategoryList(1, 1, id).enqueue(this);
                else
                    ZClient.getService(CPServer.class).lotteryCategoryList(page, limit, id).enqueue(this);
            }

            @Override
            public void onFinish(Call<ZResponse<List<LotteryCatListItem>>> call) {
                super.onFinish(call);
                if(isLoadSingle){
                    binding.swipeRefreshLayout.setEnabled(false);
                }else{
                if (mAdapter.getData() != null && mAdapter.getData().size() > 0)
                    setUIStatus(ShowStat.NORMAL);
                else
                    setUIStatus(ShowStat.NODATA);
                }
            }
        };
        callback.initSwipeRefreshLayout(binding.swipeRefreshLayout);
        loadData();
    }

    @Override
    public void loadData() {
        super.loadData();
        callback.onRefresh();
    }
}
