package com.programmsoft.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.programmsoft.models.CurrencySpinner
import com.programmsoft.room.entity.Currency
import io.reactivex.Flowable

@Dao
interface CurrencyDao {
    @Insert
    fun insert(currency: Currency)

    @Query("SELECT * FROM Currency")
    fun getAllCurrency(): List<Currency>

    @Query("SELECT id,code,title FROM Currency")
    fun getAllCurrencyForSpinner(): List<CurrencySpinner>

    @Query("SELECT id FROM Currency WHERE code =:currencyName")
    fun getCurrencyId(currencyName: String): Int

    @Query("SELECT  *FROM Currency WHERE code =:code")
    fun getCurrencyByCode(code: String): Currency

    @Query("SELECT  *FROM Currency WHERE id =:id")
    fun getCurrencyById(id: Int): Currency

    @Query("SELECT * FROM Currency WHERE buyPrice IS NOT '' AND sellPrice IS NOT '' ")
    fun getAllCurrencyIsNotEmpty(): List<Currency>

    @Query("SELECT * FROM Currency")
    fun getAllCurrencyWithFlowable(): Flowable<List<Currency>>

}