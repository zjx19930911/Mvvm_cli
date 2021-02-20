package com.iflytek.mvvm_cli.ui.home.view.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseFragment
import com.iflytek.mvvm_cli.databinding.FragmentHomeBinding
import com.iflytek.mvvm_cli.extens.observerFilter
import com.iflytek.mvvm_cli.extens.showFailedDialog
import com.iflytek.mvvm_cli.extens.startActivity
import com.iflytek.mvvm_cli.ui.home.view.adapter.HomeAdapter
import com.iflytek.mvvm_cli.ui.home.view.activity.HomeDetailActivity
import com.iflytek.mvvm_cli.ui.home.viewmodel.HomeViewModel
import com.iflytek.mvvm_cli.utils.ClickPresent
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Jianxin on 2021/1/27.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    ClickPresent {
    private val mViewModel: HomeViewModel by viewModel()
    private var page: Int = 0
    private var adapter: HomeAdapter? = null
    override fun bindVM() {
        mBinding.click = this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        adapter = HomeAdapter(activity, this)
        mBinding.topBar.setTitle("主页")
        mBinding.recycleView.layoutManager = LinearLayoutManager(activity)
        mBinding.refreshLayout.setOnRefreshListener {
            page = 0
            mViewModel.detail(page)
        }
        mBinding.refreshLayout.setOnLoadMoreListener {
            page++
            mViewModel.detail(page)
        }
        mBinding.recycleView.adapter = adapter
    }

    override fun initObserver() {
        mViewModel.detailResult.observerFilter(this, {
            dismissProgressDialog()
            mBinding.refreshLayout.finishRefresh()
            mBinding.refreshLayout.finishLoadMore()
            if (page == 0) {
                adapter?.setData(it?.datas)
            } else {
                adapter?.addData(it?.datas)
            }

        }, { msg, _ ->
            dismissProgressDialog()
            mBinding.refreshLayout.finishRefresh()
            mBinding.refreshLayout.finishLoadMore()
            showFailedDialog(msg)
        })
    }

    override fun lazyLoad() {
        showProgressDialog()
        mViewModel.detail(page)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.name -> startActivity<HomeDetailActivity>(true)
        }
    }

}