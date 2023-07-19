package com.programmsoft.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ItemViewPagerBinding
import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.App


class ViewPager2Adapter(var context: Context) :
    ListAdapter<Currency, ViewPager2Adapter.ViewHolder>(MyDiffUtil()) {

    inner class ViewHolder(binding: ItemViewPagerBinding) :RecyclerView.ViewHolder(binding.root){
        fun onBind(currency: Currency) {
            Toast.makeText(App.instance, "item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view_pager, parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class MyDiffUtil : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(
            oldItem: Currency,
            newItem: Currency,
        ): Boolean {
            return when {
                oldItem.id != newItem.id -> {
                    false
                }

                else -> true
            }

        }

        override fun areContentsTheSame(
            oldItem: Currency,
            newItem: Currency,
        ): Boolean {
            return when {
                oldItem.id != newItem.id -> {
                    false
                }
                else -> true
            }
        }
    }

}
