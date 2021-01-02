package com.cniao5.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cniao5.demo.databinding.ItemRvBinding

class BdAdapter : RecyclerView.Adapter<BdAdapter.ItVH>() {

    private val mList = mutableListOf<ItemBean>()

    init {
        for (i in 0..5) {
            mList.add(ItemBean(i, "艾特木 $i"))
        }
    }

    class ItVH(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bean: ItemBean) {
            binding.info = bean
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItVH {
        return ItVH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ItVH, position: Int) {
        holder.bind(mList[position])
    }
}

data class ItemBean(val type: Int, val text: String)