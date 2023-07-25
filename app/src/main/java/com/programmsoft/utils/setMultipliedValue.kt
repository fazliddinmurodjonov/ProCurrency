package com.programmsoft.utils

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.programmsoft.procurrency.MainActivity

@SuppressLint("SetTextI18n")
@BindingAdapter("multipliedValue")
fun setMultipliedValue(view: TextView, value: String?) {
    val firstCurrency =
        Components.db.currencyDao().getCurrencyById(MainActivity.topSpinnerPosition + 1)
    val secondCurrency =
        Components.db.currencyDao().getCurrencyById(MainActivity.bottomSpinnerPosition + 1)
    val number = value?.toDoubleOrNull() ?: 0.0
    val divisor = secondCurrency.cbPrice?.toDouble() ?: 1.0
    val multipliedValue: Double = ((number * firstCurrency.cbPrice!!.toDouble()) / divisor)
    if (multipliedValue.toString() == "0.0") {
        view.text = multipliedValue.toString()
    } else {
        view.text = "${String.format("%.2f", multipliedValue)} ${secondCurrency.code}"
    }
//        when (getCurrencySpinnerList()[topSpinnerPosition].code) {
//            "UZS" -> {
//                val number = value?.toDoubleOrNull() ?: 0.0
//                val divisor = secondCurrency.cbPrice?.toDouble() ?: 1.0
//                val multipliedValue:Double = ((number * firstCurrency.cbPrice!!.toDouble())/divisor)
//                if (multipliedValue.toString() == "0.0") {
//                    view.text = multipliedValue.toString()
//                } else {
//                    view.text = multipliedValue.toString()+secondCurrency.code
//                }
//            }
//            else -> {
//
//            }
//        }
//
//        val number = value?.toDoubleOrNull() ?: 0.0
//        val multipliedValue = number * topCurrency.cbPrice!!.toDouble()
//        if (multipliedValue.toString() == "0.0") {
//            view.text = multipliedValue.toString()
//        } else {
//            view.text = multipliedValue.toString()
//        }
}
