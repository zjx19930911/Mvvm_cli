package com.iflytek.mvvm_cli.ui.home.bean

data class AccountBean(var curPage: Int, var datas: List<Datas>) {
    class Datas {
        lateinit var title: String
    }
}