package com.iflytek.mvvm_cli.ui.splash.view
import android.animation.Animator
import android.animation.ObjectAnimator
import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseActivity
import com.iflytek.mvvm_cli.databinding.ActivitySplashBinding
import com.iflytek.mvvm_cli.ui.splash.viewmodel.SplashViewModel
import com.iflytek.mvvm_cli.extens.startActivity
import com.iflytek.mvvm_cli.ui.main.view.activity.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Jianxin on 2021/1/13.
 */

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val mViewModel: SplashViewModel by viewModel()


    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_splash


    override fun initView() {
        val animator = ObjectAnimator.ofFloat(mBinding.bg, "alpha", 0f, 1f)
        animator.duration = 2000
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity<MainActivity>()
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        animator.start()
    }

    override fun initData() {

    }

}