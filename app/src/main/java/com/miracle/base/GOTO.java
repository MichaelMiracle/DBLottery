package com.miracle.base;

import android.content.Intent;
import android.text.TextUtils;

import com.miracle.base.im.ui.ChatActivity;
import com.miracle.base.im.ui.LoginActivity;
import com.miracle.base.util.CommonUtils;
import com.miracle.base.util.ContextHolder;
import com.miracle.base.util.ToastUtil;

public class GOTO {

    public static void MainActivity() {
        App.getApp().finishAllActivity();
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), AppConfig.mainClass));
    }


    public static void LoginActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), LoginActivity.class));
    }


    public static void ChatActivity() {
        if (CommonUtils.getUser() == null) {
            LoginActivity();
        } else if (TextUtils.isEmpty(AppConfig.groupId)) {
            ToastUtil.toast("聊天室登录中,请稍后再试");
        } else {
            Intent intent = new Intent(ContextHolder.getContext(), ChatActivity.class);
            intent.putExtra("chatType", com.miracle.base.im.Constant.CHATTYPE_GROUP);
            intent.putExtra("userId", AppConfig.groupId);
            ContextHolder.getContext().startActivity(intent);
        }

    }
}
