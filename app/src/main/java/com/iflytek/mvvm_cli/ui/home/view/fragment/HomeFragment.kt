package com.iflytek.mvvm_cli.ui.home.view.fragment

import android.util.Log
import android.view.View
import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseFragment
import com.iflytek.mvvm_cli.databinding.FragmentHomeBinding
import com.iflytek.mvvm_cli.extens.observerFilter
import com.iflytek.mvvm_cli.extens.observerListFilter
import com.iflytek.mvvm_cli.extens.showFailedDialog
import com.iflytek.mvvm_cli.ui.home.viewmodel.HomeViewModel
import com.iflytek.mvvm_cli.utils.ClickPresent
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Jianxin on 2021/1/27.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    ClickPresent {
    private val mViewModel: HomeViewModel by viewModel()

    override fun bindVM() {
        mBinding.click = this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        mBinding.topBar.setTitle("主页")
    }

    override fun lazyLoad() {
        showProgressDialog()
        mViewModel.detail()
        mViewModel.detailResult.observerListFilter(this, {
            dismissProgressDialog()
        }, { msg, _ ->
            dismissProgressDialog()
            showFailedDialog(msg)
        })
    }

    override fun onClick(v: View?) {

    }

}