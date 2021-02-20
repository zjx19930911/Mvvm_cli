package com.iflytek.mvvm_cli.ui.user.view.fragment

import android.util.Log
import android.view.View

import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseFragment
import com.iflytek.mvvm_cli.databinding.FragmentUserBinding
import com.iflytek.mvvm_cli.extens.observerFilter
import com.iflytek.mvvm_cli.extens.showFailedDialog
import com.iflytek.mvvm_cli.extens.showSuccessDialog
import com.iflytek.mvvm_cli.ui.user.viewmodel.UserViewModel
import com.iflytek.mvvm_cli.utils.ClickPresent
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Jianxin on 2021/1/27.
 */
class UserFragment : BaseFragment<FragmentUserBinding>(),
    ClickPresent {
    private val mViewModel: UserViewModel by viewModel()
    override fun bindVM() {
        mBinding.click = this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun initView() {
        mBinding.topBar.setTitle("我的")
    }

    override fun initObserver() {
        mViewModel.detailResult.observerFilter(this, {
            dismissProgressDialog()
            showSuccessDialog("网络获取到的页数:" + it?.curPage)
        }, { msg, _ ->
            dismissProgressDialog()
            showFailedDialog(msg)
        })
        mViewModel.queryResult.observerFilter(this, {
            dismissProgressDialog()
            showSuccessDialog("查询到的数据:$it")
        }, { msg, _ ->
            dismissProgressDialog()
            showFailedDialog(msg)
        })
        mViewModel.insertResult.observerFilter(this, {
            dismissProgressDialog()
            showSuccessDialog("插入成功！")
        }, { msg, _ ->
            dismissProgressDialog()
            showFailedDialog(msg)
        })
    }

    override fun lazyLoad() {
        Log.e("MineFragment", "MineFragment:initData")
        showProgressDialog()
        mViewModel.detail()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.query -> {
                showProgressDialog()
                mViewModel.query()
            }
            R.id.insert -> {
                showProgressDialog()
                mViewModel.insert()
            }
        }
    }



}