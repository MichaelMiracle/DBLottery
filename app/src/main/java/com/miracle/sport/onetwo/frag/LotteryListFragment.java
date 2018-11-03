package com.miracle.sport.onetwo.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.network.PageLoadCallback;
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
    }

    @Override
    public int getLayout() {
        return R.layout.activity_lottery_detail;
    }

    @Override
    public void initView() {
//        lotteryCatData = (LotteryCatTitleItem) getIntent().getSerializableExtra(KEY_DATA);


        mAdapter = new LotteryCategoryListAdapter(mContext);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        binding.lotteryZoushiTv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent act = new Intent(mContext, LottoTrendActivity.class);
//                startActivity(act);
//            }
//        });
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
        if(isLoadSingle){
            binding.swipeRefreshLayout.setSize(0);
        }
        mAdapter.setShowSingle(isLoadSingle);

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
//                    setShowNodata(false);
                else
                    setUIStatus(ShowStat.NODATA);
//                    setShowNodata(true);
                }
            }
        };
        callback.initSwipeRefreshLayout(binding.swipeRefreshLayout);
        callback.onRefresh();
    }
}
