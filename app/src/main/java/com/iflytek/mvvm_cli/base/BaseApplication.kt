package com.iflytek.mvvm_cli.base

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.iflytek.mvvm_cli.R
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator


/**
 * Created by Jianxin on 2021/1/26.
 */

open class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        QMUISwipeBackActivityManager.init(this)
        Stetho.initializeWithDefaults(this);
        //设置全局的Header构建器
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
//            layout.setPrimaryColorsId(R.color.teal_200, R.color.white) //全局设置主题颜色
            ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
        //设置全局的Footer构建器
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(
                context
            ).setDrawableSize(20f)
        }
    }

}