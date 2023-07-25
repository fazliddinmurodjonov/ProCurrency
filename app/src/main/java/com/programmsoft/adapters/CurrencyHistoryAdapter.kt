package com.programmsoft.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ItemCurrencyHistoryBinding
import com.programmsoft.room.entity.Currency

class CurrencyHistoryAdapter :
    ListAdapter<Currency, CurrencyHistoryAdapter.CurrencyHistoryViewHolder>(MyDiffUtil()) {

    inner class CurrencyHistoryViewHolder(val binding: ItemCurrencyHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(currency: Currency) {
            binding.currency = currency
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CurrencyHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCurrencyHistoryBinding>(
            inflater,
            R.layout.item_currency_history,
            parent,
            false
        )
        return CurrencyHistoryViewHolder(binding)

    }


    override fun onBindViewHolder(holder: CurrencyHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
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
