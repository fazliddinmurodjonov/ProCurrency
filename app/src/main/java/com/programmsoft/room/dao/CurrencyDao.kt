package com.programmsoft.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.programmsoft.room.entity.Currency

@Dao
interface CurrencyDao {
    @Insert
    fun insert(currency: Currency)

    @Query("SELECT * FROM Currency")
    fun getAllCurrency(): List<Currency>
}