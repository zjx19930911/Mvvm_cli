package com.iflytek.mvvm_cli.ui.main.view.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.lifecycleScope
import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.base.BaseActivity
import com.iflytek.mvvm_cli.databinding.ActivityMainBinding
import com.iflytek.mvvm_cli.ui.home.view.fragment.HomeFragment
import com.iflytek.mvvm_cli.ui.main.viewmodel.MainViewModel
import com.iflytek.mvvm_cli.ui.user.view.fragment.UserFragment
import com.iflytek.mvvm_cli.utils.ClickPresent

import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.tab.QMUITab
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>(),
        ClickPresent {
    //di
//    private val mViewModel: AnimalViewModel by viewModel() {
//        parametersOf(
//            Animal("dog", 0),
//            application
//        )
//    }
    private val mViewModel: MainViewModel by viewModel()
    var fragmentList: MutableList<Fragment> = ArrayList()
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun bindVM() {
        mBinding.click = this
    }

    override fun initView() {
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        initTab()
    }

    private fun initTab() {
        val normalColor = resources.getColor(R.color.qmui_config_color_gray_2)
        val selectColor = resources.getColor(R.color.teal_200)
        val home = mBinding.tabs.tabBuilder()
            .setColor(normalColor, selectColor)
            .setText("首页")
            .setNormalDrawable(resources.getDrawable(R.mipmap.icon_tab_home_selected))
            .setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setDynamicChangeIconColor(true)
            .build(this@MainActivity)
        mBinding.tabs.addTab(home)
        val mine = mBinding.tabs.tabBuilder()
            .setColor(normalColor, selectColor)
            .setText("我的")
            .setNormalDrawable(resources.getDrawable(R.mipmap.icon_tab_mine_selected))
            .setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setDynamicChangeIconColor(true)
            .build(this@MainActivity)
        mBinding.tabs.addTab(mine)
        val mainFragment = HomeFragment()
        val mineFragment = UserFragment()
        fragmentList.add(mainFragment)
        fragmentList.add(mineFragment)
        //        mTabSegment.notifyDataChanged();
        //        mTabSegment.notifyDataChanged();
        mBinding.viewPager.offscreenPageLimit = 5
        mBinding.viewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getCount(): Int {
                return fragmentList.size
            }
        }
        mBinding.tabs.setupWithViewPager(mBinding.viewPager, false)
    }

    override fun initData() {
//        mViewModel.detailResult.observerFilter(this, {
//            dismissProgressDialog()
//            showSuccessDialog(it?.name)
//        }, { message, _ ->
//            dismissProgressDialog()
//            showFailedDialog(message)
//        })
    }

    override fun onClick(v: View?) {
//        if (v?.id == R.id.btn_insert) {
//            mViewModel.insert(User("插入的user"))
//        } else if (v?.id == R.id.btn_query) {
//            showProgressDialog()
//            mViewModel.detail()
//        }
    }
}


