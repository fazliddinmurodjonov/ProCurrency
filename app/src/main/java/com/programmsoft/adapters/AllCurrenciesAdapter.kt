package com.programmsoft.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ItemCurrencyBinding
import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.App

class AllCurrenciesAdapter : ListAdapter<Currency, AllCurrenciesAdapter.AllCurrenciesViewHolder>(MyDiffUtil()) {
    lateinit var itemCalculatorClick: OnItemCalculatorClickListener

    fun interface OnItemCalculatorClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemCalculatorClickListener(listener: OnItemCalculatorClickListener) {
        itemCalculatorClick = listener
    }

    inner class AllCurrenciesViewHolder(val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DiscouragedApi")
        fun onBind(currency: Currency,position: Int) {
            val flagId = if (currency.code == "TRY") {
                App.instance.resources.getIdentifier(
                    "flag_${currency.code?.lowercase()}",
                    "drawable",
                    App.instance.packageName
                )
            } else {
                App.instance.resources.getIdentifier(
                    currency.code?.lowercase(),
                    "drawable",
                    App.instance.packageName
                )
            }
            binding.flag.setImageResource(flagId)
            binding.currency = currency
            binding.calculateCard.setOnClickListener {
                itemCalculatorClick.onClick(position)

            }
        }
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

                oldItem.buyPrice != newItem.buyPrice -> {
                    false
                }

                oldItem.sellPrice != newItem.sellPrice -> {
                    false
                }

                oldItem.code != newItem.code -> {
                    false
                }

                oldItem.cbPrice != newItem.cbPrice -> {
                    false
                }

                oldItem.time != newItem.time -> {
                    false
                }

                oldItem.date != newItem.date -> {
                    false
                }

                oldItem.title != newItem.title -> {
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

                oldItem.buyPrice != newItem.buyPrice -> {
                    false
                }

                oldItem.sellPrice != newItem.sellPrice -> {
                    false
                }

                oldItem.code != newItem.code -> {
                    false
                }

                oldItem.cbPrice != newItem.cbPrice -> {
                    false
                }

                oldItem.time != newItem.time -> {
                    false
                }

                oldItem.date != newItem.date -> {
                    false
                }

                oldItem.title != newItem.title -> {
                    false
                }
                else -> true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCurrenciesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCurrencyBinding>(
            inflater,
            R.layout.item_currency,
            parent,
            false
        )
        return AllCurrenciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllCurrenciesViewHolder, position: Int) {
        holder.onBind(getItem(position),position)
    }

}