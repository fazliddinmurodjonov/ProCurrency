package com.programmsoft.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.programmsoft.models.CurrencySpinner
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ItemCurrencyDialogBinding
import com.programmsoft.utils.App

class DialogSpinnerAdapter(val list: ArrayList<CurrencySpinner>) :
    RecyclerView.Adapter<DialogSpinnerAdapter.DialogSpinnerViewHolder>() {
    lateinit var itemClick: OnItemClickListener

    fun interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClick = listener
    }

    inner class DialogSpinnerViewHolder(val binding: ItemCurrencyDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DiscouragedApi")
        fun onBind(currencySpinner: CurrencySpinner, position: Int) {
            binding.currencySpinner = currencySpinner
            val flagId = if (currencySpinner.code == "TRY") {
                App.instance.resources.getIdentifier(
                    "flag_${currencySpinner.code?.lowercase()}",
                    "drawable",
                    App.instance.packageName
                )
            } else {
                App.instance.resources.getIdentifier(
                    currencySpinner.code?.lowercase(),
                    "drawable",
                    App.instance.packageName
                )
            }

            binding.flag.setImageResource(flagId)
            binding.root.setOnClickListener {
                itemClick.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogSpinnerViewHolder {
        val binding = DataBindingUtil.inflate<ItemCurrencyDialogBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_currency_dialog,
            parent,
            false
        )
        return DialogSpinnerViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DialogSpinnerViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

}