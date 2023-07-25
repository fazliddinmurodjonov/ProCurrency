package com.programmsoft.repository

import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.Components
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CurrenciesRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun getCurrencies(): Boolean {
        val currencies = coroutineScope.async {
            Components.serviceOfCurrencies.getCurrencies()
        }
        val response = currencies.await()
        if (response.isSuccessful) {
            val currenciesList = response.body()
            var time = ""
            var date = ""
            currenciesList?.forEach {
                if (it.date.length > 12) {
                    time = Components.getTimeOrDate(it.date, "time")
                    date = Components.getTimeOrDate(it.date, "date")
                } else {
                    time = Components.getHoursAndMinutes()
                    date = it.date
                }
                val currency = Currency(
                    it.cb_price,
                    it.code,
                    it.nbu_buy_price,
                    it.nbu_cell_price,
                    it.title,
                    time,
                    date
                )
                Components.db.currencyDao().insert(currency)
            }
            val uzsCurrency = Currency("1", "UZS", "", "", "O‘zbek so‘mi", time, date)
            Components.db.currencyDao().insert(uzsCurrency)

        }
        return true
    }
}