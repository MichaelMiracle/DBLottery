package com.miracle.base.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.miracle.R;
import com.miracle.databinding.ZPlaceholderBinding;

/**
 * Created by Michael on 2018/11/3 10:38 (星期六)
 */
public class ZPlaceHolder extends LinearLayout {
    private ZPlaceholderBinding binding;

    public ZPlaceHolder(Context context) {
        this(context, null);
    }

    public ZPlaceHolder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.z_placeholder, this, true);
    }

    public void setLoading() {
        binding.progressBar.setVisibility(VISIBLE);
        binding.ivEmpty.setVisibility(GONE);
        binding.tvMessage.setText("加载中...");
    }

    public void setEmpty() {
        binding.progressBar.setVisibility(GONE);
        binding.ivEmpty.setVisibility(VISIBLE);
        binding.tvMessage.setText("暂无数据，点击重试");
    }

    public void setError() {
        binding.progressBar.setVisibility(GONE);
        binding.ivEmpty.setVisibility(VISIBLE);
        binding.tvMessage.setText("加载失败，点击重试");
    }
}
