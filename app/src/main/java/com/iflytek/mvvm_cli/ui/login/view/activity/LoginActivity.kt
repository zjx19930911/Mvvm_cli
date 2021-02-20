package com.iflytek.mvvm_cli.ui.login.view.activity

import android.content.Intent
import android.view.View

import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseActivity
import com.iflytek.mvvm_cli.databinding.ActivityLoginBinding
import com.iflytek.mvvm_cli.extens.observerFilter
import com.iflytek.mvvm_cli.extens.showFailedDialog
import com.iflytek.mvvm_cli.extens.showSuccessDialog
import com.iflytek.mvvm_cli.ui.login.viewmodel.LoginViewModel
import com.iflytek.mvvm_cli.utils.ClickPresent
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(),
    ClickPresent {

    private val mViewModel: LoginViewModel by viewModel()

    private var oldIntent: Intent? = null

    override fun bindVM() {
        mBinding.click = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        oldIntent = intent.getParcelableExtra("intent")
        mBinding.topBar.setTitle("登录")
    }

    override fun initData() {
        mViewModel.loginResult.observerFilter(this, {
            dismissProgressDialog()
            showSuccessDialog("登录成功") {
                startActivity(oldIntent)
                finish()
            }
        }, { message, _ ->
            dismissProgressDialog()
            showFailedDialog(message)
        })
    }

    override fun onClick(v: View?) {
        showProgressDialog()
        mViewModel.login(mBinding.editAcc.text.toString(), mBinding.editPwd.text.toString())
    }
}
