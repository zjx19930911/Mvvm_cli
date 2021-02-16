package com.iflytek.mvvm_cli.base

import android.app.Application
import com.facebook.stetho.Stetho
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager

/**
 * Created by Jianxin on 2021/1/26.
 */

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        QMUISwipeBackActivityManager.init(this)
        Stetho.initializeWithDefaults(this);
    }

}