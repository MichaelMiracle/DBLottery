package com.miracle.sport.onetwo.frag;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gongwen.marqueen.SimpleMF;
import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.im.ui.MainActivity;
import com.miracle.base.util.ContextHolder;
import com.miracle.base.view.CircleImageView;
import com.miracle.databinding.FragmentCpMainTopBinding;
import com.miracle.sport.onetwo.inter.CallBackListener;
import com.miracle.sport.onetwo.netbean.CpListItem;
import com.miracle.sport.onetwo.netbean.LotteryCatTitleItem;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

//FragmentCpMainBinding
public class FragmentLotteryMain extends BaseFragment<FragmentCpMainTopBinding>{
    Banner banner;
    List<String> Mardatas = new ArrayList<String>();
    FragmentCpMainTopBinding topBinding;

    @Override
    public int getLayout() {
        return R.layout.fragment_cp_main;
    }

    @Override
    public void initView() {
//        showTitle();
        Log.i("TAG", "initView: xxxxxxxxxxx 1");
        topBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_cp_main_top, null, false);
        final FragCpItemList subFrag = (FragCpItemList) getActivity().getSupportFragmentManager().findFragmentById(R.id.list_frag);
        subFrag.setShowBanner(false);
        subFrag.setCallBackListener(new CallBackListener<List<CpListItem>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish(List<CpListItem> data) {
//                if(data != null && data.size() > 0)
//                {
                    setUIStatus(ShowStat.NORMAL);
//                    topBinding.getRoot().setVisibility(View.VISIBLE);
//                }else{
//                    topBinding.getRoot().setVisibility(View.GONE);
//                    setUIStatus(ShowStat.NODATA);
//                }
            }
        });

        initBanner();
        initMard();
//        initHSuserBar();
        initTicket();

        View.OnClickListener subTitleClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() instanceof MainActivity)
                {
                    MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.switchTabIndex(2);
                }
            }
        };
        topBinding.mainFragMore1.setOnClickListener(subTitleClick);
        topBinding.mainFragMore2.setOnClickListener(subTitleClick);

        //
        topBinding.getRoot().findViewById(R.id.main_farg_tryrand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
//                mainActivity.switchTabIndex(1);
            }
        });

        //
        topBinding.getRoot().findViewById(R.id.main_frag_more3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() instanceof MainActivity)
                {
                    MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.startMenuAct(MainActivity.M_2);
                }
            }
        });

        LotteryListFragment fragment = (LotteryListFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.main_farg_newnum_frag);
        fragment.setShowSingle(true);
        LotteryCatTitleItem lotteryCatTitleItem = new LotteryCatTitleItem();
        lotteryCatTitleItem.setId(1);
        fragment.setLotteryCatData(lotteryCatTitleItem);

//        RecyclerView main_farg_rv = topBinding.getRoot().findViewById(R.id.main_farg_rv);
//        main_farg_rv.setAdapter();

        Log.i("TAG", "initView: xxxxxxxxxxx 4");
        subFrag.mAdapter.addHeaderView(topBinding.getRoot());
        subFrag.mAdapter.notifyDataSetChanged();
//        subFrag.binding.getRoot().requestLayout();
    }

    private void initTicket() {
        View.OnClickListener vticktClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() != null && getActivity() instanceof MainActivity)
                {
                    MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.showChatAct(getActivity());
                }
            }
        };
        topBinding.mainLl1.setOnClickListener(vticktClick);
        topBinding.mainLl2.setOnClickListener(vticktClick);
    }

    private void initHSuserBar() {
        HorizontalScrollView main_frag_hs2 = topBinding.getRoot().findViewById(R.id.main_frag_hs2);
        LinearLayout main_frag_hs_ll = topBinding.getRoot().findViewById(R.id.main_frag_hs_ll);
        List<Bitmap> bitmaps = null;
        List<String> userName = null;
//        try {
//            bitmaps = RandUtils.randImgs(mContext,7);
//            userName = RandUtils.randUserName(mContext, 7);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() != null && getActivity() instanceof MainActivity)
                {
                    MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.showChatAct(getActivity());
                }
            }
        };
        for(int i = 0; i < bitmaps.size(); i++){
            View view = getLayoutInflater().inflate(R.layout.main_frag_hs1_item, null);
            CircleImageView iv = (CircleImageView)view.findViewById(R.id.main_farg_hs1_iv);
            ((TextView)view.findViewById(R.id.main_farg_hs1_tv1)).setText(userName.get(i));
            iv.setImageBitmap(bitmaps.get(i));
            view.setOnClickListener(onClickListener);
            main_frag_hs_ll.addView(view);
        }
    }

    private void initMard() {
        Mardatas.add("新中奖号码已更新");
//        Mardatas.add("评论员:["+RandUtils.randUserName(mContext,1).get(0)+"]预测帝再次预测到90%的中奖号码");
        SimpleMF<String> marqueeFactory2 = new SimpleMF(mContext);
        marqueeFactory2.setData(Mardatas);
        topBinding.marqueeView3.setMarqueeFactory(marqueeFactory2);
        topBinding.marqueeView3.startFlipping();
    }

    private void initBanner() {
        banner = topBinding.mainFarg1Banner;
        ArrayList images = new ArrayList<>();
        images.add(R.mipmap.b3);
        images.add(R.mipmap.b5);
        banner.setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(ContextHolder.getContext()).load(path).into(imageView);
            }
        });
        banner.setImages(images);
        banner.setDelayTime(1000 * 3);
        banner.start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadData() {

    }
}
