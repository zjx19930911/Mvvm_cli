package com.iflytek.mvvm_cli.ui.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iflytek.mvvm_cli.R
import com.iflytek.mvvm_cli.databinding.ItemHomeBinding
import com.iflytek.mvvm_cli.ui.home.bean.AccountBean

/**
 * Created by Jianxin on 2021/2/19.
 */
class HomeAdapter(
    var context: Context?,
    var click: View.OnClickListener?
) :
    RecyclerView.Adapter<HomeAdapter.VH>() {

    private var list: MutableList<AccountBean.Datas>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.mBinding?.name?.text = list?.get(position)?.title
        holder.mBinding?.name?.setOnClickListener(click)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setData(datas: List<AccountBean.Datas>?) {
        this.list = datas?.toMutableList()
        notifyDataSetChanged()
    }

    fun addData(datas: List<AccountBean.Datas>?) {
        datas?.let {
            this.list?.addAll(it)
            notifyDataSetChanged()
        }

    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mBinding = DataBindingUtil.bind<ItemHomeBinding>(itemView)
    }
}