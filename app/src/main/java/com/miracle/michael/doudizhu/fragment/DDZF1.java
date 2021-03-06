package com.miracle.michael.doudizhu.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.GOTO;
import com.miracle.base.network.PageLoadCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZService;
import com.miracle.base.util.ContextHolder;
import com.miracle.databinding.BannerLayoutBinding;
import com.miracle.databinding.F1DdzBinding;
import com.miracle.michael.doudizhu.activity.DDZNewsDetailActivity;
import com.miracle.michael.doudizhu.adapter.DDZNewsListAdapter;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class DDZF1 extends BaseFragment<F1DdzBinding> {

    private DDZNewsListAdapter mAdapter;
    private PageLoadCallback callBack;

    private List<String> images;

    @Override
    public int getLayout() {
        return R.layout.f1_ddz;
    }

    @Override
    public void initView() {
        binding.titleBar.showLeft(drawerLayout != null);
        mAdapter = new DDZNewsListAdapter(mContext);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        addHeadView();

//        int resId = R.anim.layout_animation_fall_down;
//        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        binding.recyclerView.setLayoutAnimation(animation);
        initCallback();
    }

    private void addHeadView() {
        BannerLayoutBinding bannerBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.banner_layout, null, false);
        initBanner(bannerBinding);
        mAdapter.addHeaderView(bannerBinding.getRoot());
    }

    private void initBanner(BannerLayoutBinding bannerBinding) {
        images = new ArrayList<>();
        images.add("file:///android_asset/lottery/1.png");
        images.add("file:///android_asset/lottery/2.png");
        images.add("file:///android_asset/lottery/3.png");
        bannerBinding.banner.setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(ContextHolder.getContext()).load(path).into(imageView);
            }
        }).start();

        bannerBinding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
    }


    private void initCallback() {
        binding.tvContactCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GOTO.CustomerServiceActivity(mContext);
            }
        });
        callBack = new PageLoadCallback(mAdapter, binding.recyclerView) {
            @Override
            public void requestAction(int page, int limit) {
                ZClient.getService(ZService.class).getDDZNewsList(11, page, limit).enqueue(callBack);
            }
        };
        callBack.initSwipeRefreshLayout(binding.swipeRefreshLayout);
    }


    @Override
    public void onResume() {
        super.onResume();
        callBack.onRefresh();
    }

    @Override
    public void initListener() {
        binding.titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout != null)
                    drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, DDZNewsDetailActivity.class).putExtra("id", mAdapter.getItem(position).getId()));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private DrawerLayout drawerLayout;

    public DDZF1 setDrawer(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
        return this;
    }
}
