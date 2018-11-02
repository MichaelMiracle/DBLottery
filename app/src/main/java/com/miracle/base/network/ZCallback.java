package com.miracle.base.network;

import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.miracle.base.util.GsonUtil;
import com.miracle.base.util.ToastUtil;
import com.miracle.base.util.sqlite.SQLiteUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ZCallback<T> implements Callback<T> {

    private SwipeRefreshLayout swipeRefreshLayout;
    private Dialog dialog;
    //缓存key
    private String key;

    public ZCallback() {
    }

    public ZCallback(String key) {
        this.key = key;
    }

    public ZCallback(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public ZCallback(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (body instanceof ZResponse) {
            ZResponse zResponse = (ZResponse) body;
            if (zResponse.getCode() == 200 || zResponse.getCode() == 0) {
                if (!TextUtils.isEmpty(key)) {
                    SQLiteUtil.saveString(key, GsonUtil.obj2Json(zResponse));
                }
                onSuccess(body);
                onFinish();
            } else {
                onFailure(call, new Throwable(zResponse.getMessage()));
            }
        } else {
            onFailure(call, new Throwable("返回数据格式不正确！"));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ToastUtil.toast(t.getMessage());
        onFinish();
    }

    public abstract void onSuccess(T data);

    public void onFinish() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public String getKey() {
        return key;
    }
}
