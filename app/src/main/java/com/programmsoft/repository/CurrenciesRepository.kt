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
            currenciesList?.forEach {
                val currency = Currency(it.cb_price,
                    it.code,
                    it.date,
                    it.nbu_buy_price,
                    it.nbu_cell_price,
                    it.title)
                Components.db.currencyDao().insert(currency)
            }
        }
        return true
    }
}