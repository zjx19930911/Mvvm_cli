package com.iflytek.mvvm_cli.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog

/**
 * Created by Jianxin on 2021/1/27.
 */

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    protected lateinit var mBinding: VDB
    private var isLoad = false
    private val progressDialog: Dialog by lazy {
        val dialog = QMUITipDialog.Builder(activity)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord("正在加载").create()
        println("dialog init!")
        dialog
    }

    private fun <T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes contentLayoutId: Int
    ): T = lazy {
        DataBindingUtil.inflate<T>(inflater, contentLayoutId, null, false)
            .apply { this.lifecycleOwner = this@BaseFragment }
    }.value


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = binding<VDB>(inflater, getLayoutId())
        bindVM()
        initView()
        initObserver()
        return mBinding.root
    }


    abstract fun bindVM()

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initObserver()

    override fun onResume() {
        super.onResume()
        if (!isLoad) {
            isLoad = true
            lazyLoad()
        }
    }

    /**
     * 初始化数据
     */
    abstract fun lazyLoad()

    protected open fun showProgressDialog(msg: String = "正在加载") {
        progressDialog.show()
    }

    protected open fun dismissProgressDialog() {
        progressDialog.dismiss()
    }
}