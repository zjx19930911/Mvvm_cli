package com.iflytek.mvvm_cli.ui.user.view.fragment

import android.util.Log
import android.view.View

import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseFragment
import com.iflytek.mvvm_cli.databinding.FragmentUserBinding
import com.iflytek.mvvm_cli.utils.ClickPresent


/**
 * Created by Jianxin on 2021/1/27.
 */
class UserFragment : BaseFragment<FragmentUserBinding>(),
        ClickPresent {
    override fun bindVM() {
        mBinding.click = this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun initView() {
        mBinding.topBar.setTitle("我的")
    }

    override fun lazyLoad() {
        Log.e("MineFragment", "MineFragment:initData")
    }

    override fun onClick(v: View?) {

    }

}