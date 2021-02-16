package com.iflytek.mvvm_cli


import com.iflytek.mvvm_cli.base.BaseApplication
import com.iflytek.mvvm_cli.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Jianxin on 2021/1/11.
 */
class MyApplication : BaseApplication() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            //开始启动koin
            androidContext(applicationContext)//这边传Application对象,这样你注入的类中,需要app对象的时候,可以直接使用
            modules(appModule)//这里面传各种被注入的模块对象,支持多模块注入
        }
    }

}