package com.iflytek.mvvm_cli.di

import com.iflytek.mvvm_cli.extens.retrofit
import com.iflytek.mvvm_cli.room.AppDatabase
import com.iflytek.mvvm_cli.ui.home.api.HomeApi
import com.iflytek.mvvm_cli.ui.home.repository.HomeRepository
import com.iflytek.mvvm_cli.ui.home.viewmodel.HomeViewModel
import com.iflytek.mvvm_cli.ui.main.viewmodel.MainViewModel
import com.iflytek.mvvm_cli.ui.splash.viewmodel.SplashViewModel
import com.iflytek.mvvm_cli.ui.user.api.UserApi
import com.iflytek.mvvm_cli.ui.user.repository.UserRepository
import com.iflytek.mvvm_cli.ui.user.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        MainViewModel(application = androidApplication())
    }
}

val homeViewModelModule = module {
    viewModel {
        HomeViewModel(
            HomeRepository(retrofit(HomeApi::class.java)),
            application = androidApplication()
        )
    }
}

val userViewModelModule = module {
    viewModel {
        UserViewModel(
            UserRepository(
                retrofit(UserApi::class.java),
                AppDatabase.getInstance(androidApplication()).userDao()
            ), application = androidApplication()
        )
    }
}

val splashViewModel = module {
    viewModel {
        SplashViewModel(application = androidApplication())
    }
}


val appModuleList by lazy {
    {
        val list: MutableList<Module> = ArrayList();
        list.add(mainViewModelModule)
        list.add(homeViewModelModule)
        list.add(splashViewModel)
        list.add(userViewModelModule)
        list.toList()
    }
}

var appModule = appModuleList()