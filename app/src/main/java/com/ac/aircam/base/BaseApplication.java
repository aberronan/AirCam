package com.ac.aircam.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by chengzhirui on 2018/9/8.
 */
public class BaseApplication extends Application {
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ARouter.init(this);
    }

    @Nullable
    public static Context getContext() {
        return mContext;
    }
}
