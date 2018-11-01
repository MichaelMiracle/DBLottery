package com.miracle.base.network;

import com.miracle.base.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Michael on 2018/10/19 17:23 (星期五)
 */
public interface ZService {

    /*▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮小米▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮*/

    /**
     * 登录
     */
    @Headers({"BaseUrl:mi"})
    @POST("loginSet")
    Call<ZResponse<UserBean>> login(@Query("username") String username, @Query("password") String password);

    /**
     * 注册
     */
    @Headers({"BaseUrl:mi"})
    @POST("login")
    Call<ZResponse<UserBean>> register(@Query("username") String username, @Query("password") String password, @Query("nickname") String nickname);

}
