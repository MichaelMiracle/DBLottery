package com.miracle.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.miracle.base.im.ui.ChatActivity;
import com.miracle.base.im.ui.LoginActivity;
import com.miracle.base.util.CommonUtils;
import com.miracle.base.util.ContextHolder;
import com.miracle.base.util.ToastUtil;

public class GOTO {

    public static void MainActivity(Context context) {
        App.getApp().finishAllActivity();
        context.startActivity(new Intent(context, AppConfig.mainClass));
    }


    public static void LoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    public static void ChatActivity(Context context) {
        if (CommonUtils.getUser() == null) {
            LoginActivity(context);
        } else if (TextUtils.isEmpty(AppConfig.groupId)) {
            ToastUtil.toast("聊天室登录中,请稍后再试");
        } else {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("chatType", com.miracle.base.im.Constant.CHATTYPE_GROUP);
            intent.putExtra("userId", AppConfig.groupId);
            context.startActivity(intent);
        }

    }
}
