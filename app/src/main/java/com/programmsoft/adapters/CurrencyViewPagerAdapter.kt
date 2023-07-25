package com.programmsoft.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ItemCurrencyViewPagerBinding
import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.App


class CurrencyViewPagerAdapter() :
    ListAdapter<Currency, CurrencyViewPagerAdapter.CurrencyViewPagerHolder>(MyDiffUtil()) {
    private lateinit var layoutInflater: LayoutInflater

    inner class CurrencyViewPagerHolder(var binding: ItemCurrencyViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DiscouragedApi")
        fun onBind(currency: Currency) {
            binding.currency = currency

            val flagId = if (currency.code == "TRY") {
                App.instance.resources.getIdentifier(
                    "flag_${currency.code!!.lowercase()}",
                    "drawable",
                    App.instance.packageName
                )
            } else {
                App.instance.resources.getIdentifier(
                    currency.code!!.lowercase(),
                    "drawable",
                    App.instance.packageName
                )
            }
            binding.flag.setImageResource(flagId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewPagerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCurrencyViewPagerBinding>(
            inflater,
            R.layout.item_currency_view_pager,
            parent,
            false
        )
        return CurrencyViewPagerHolder(binding)

    }


    override fun onBindViewHolder(holder: CurrencyViewPagerHolder, position: Int) {
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
