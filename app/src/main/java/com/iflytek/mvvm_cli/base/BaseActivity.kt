package com.iflytek.mvvm_cli.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog


/**
 * Created by Jianxin on 2021/1/12.
 */
abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mBinding: VDB
    private val progressDialog: Dialog by lazy {
        val dialog = QMUITipDialog.Builder(this)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord("正在加载").create()
        println("dialog init!")
        dialog
    }

    private fun <T : ViewDataBinding> binding(@LayoutRes contentLayoutId: Int): T {
        return lazy {
            DataBindingUtil.setContentView<T>(this, contentLayoutId)
                .apply { this.lifecycleOwner = this@BaseActivity }
        }.value
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.translucent(this)
        mBinding = binding<VDB>(getLayoutId())
        bindVM()
        initView()
        initData()
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
    abstract fun initData()


    protected open fun showProgressDialog(msg: String = "正在加载") {
        progressDialog.show()
    }

    protected open fun dismissProgressDialog() {
        progressDialog.dismiss()
    }


}

