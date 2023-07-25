package com.programmsoft.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.programmsoft.models.CurrencySpinner
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ItemCurrencySpinnerBinding
import com.programmsoft.utils.App


class SpinnerAdapter(private val context: Context, private val list: List<CurrencySpinner>) :
    BaseAdapter() {

    override fun isEnabled(position: Int): Boolean = position != 0
    override fun getCount(): Int = list.size
    override fun getItem(position: Int): CurrencySpinner = list[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return super.getDropDownView(position, convertView, parent)
    }

    @SuppressLint("ViewHolder", "ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = DataBindingUtil.inflate<ItemCurrencySpinnerBinding>(
            LayoutInflater.from(parent!!.context),
            R.layout.item_currency_spinner,
            parent,
            false
        )
        binding.currencySpinner = list[position]
        val flagId = if (list[position].code == "TRY") {
            App.instance.resources.getIdentifier(
                "flag_${list[position].code!!.lowercase()}",
                "drawable",
                App.instance.packageName
            )
        } else {
            App.instance.resources.getIdentifier(
                list[position].code!!.lowercase(),
                "drawable",
                App.instance.packageName
            )
        }

        binding.currency.text = list[position].code
        binding.flag.setImageResource(flagId)

        return binding.root
    }

}