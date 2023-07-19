package com.programmsoft.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.programmsoft.procurrency.databinding.ItemViewPagerBinding
import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.App


class CurrencySliderAdapter(var list: ArrayList<Currency>, var context: Context) :
    RecyclerView.Adapter<CurrencySliderAdapter.CurrencySliderViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    inner class CurrencySliderViewHolder(var binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(currency: Currency) {
            binding.buyingCurrency.text = "AAAAAA"
            Toast.makeText(App.instance, "item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencySliderViewHolder {
//        layoutInflater = LayoutInflater.from(parent.context)
//        val currencySliderBinding: ItemViewPagerBinding =
//        DataBindingUtil.inflate(layoutInflater, R.layout.item_view_pager, parent, false)
//        return CurrencySliderViewHolder(currencySliderBinding)

            return CurrencySliderViewHolder(
            ItemViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CurrencySliderViewHolder, position: Int) {
        holder.onBind(list[position])
    }


//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int,
//    ): ViewHolder {
//        return ViewHolder(
//            DataBindingUtil.inflate(
//                LayoutInflater.from(parent.context),
//                R.layout.item_view_pager, parent, false
//            )
//        )
//    }
//
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//    }
//
//    class MyDiffUtil : DiffUtil.ItemCallback<Currency>() {
//        override fun areItemsTheSame(
//            oldItem: Currency,
//            newItem: Currency,
//        ): Boolean {
//            return when {
//                oldItem.id != newItem.id -> {
//                    false
//                }
//
//                else -> true
//            }
//
//        }
//
//        override fun areContentsTheSame(
//            oldItem: Currency,
//            newItem: Currency,
//        ): Boolean {
//            return when {
//                oldItem.id != newItem.id -> {
//                    false
//                }
//                else -> true
//            }
//        }
//    }

}
