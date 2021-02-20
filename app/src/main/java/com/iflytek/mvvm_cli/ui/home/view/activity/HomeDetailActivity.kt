package com.iflytek.mvvm_cli.ui.home.view.activity

import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseActivity
import com.iflytek.mvvm_cli.databinding.ActivityHomeDetailBinding
import com.iflytek.mvvm_cli.extens.showSuccessDialog

/**
 * Created by Jianxin on 2021/2/20.
 */
class HomeDetailActivity : BaseActivity<ActivityHomeDetailBinding>() {
    override fun bindVM() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home_detail
    }

    override fun initView() {
        mBinding.topBar.addLeftBackImageButton().setOnClickListener {
            finish()
        }
        mBinding.topBar.setTitle("主页详情")
        mBinding.name.setOnClickListener{
            showSuccessDialog("asd")
        }
    }

    override fun initData() {
    }
}