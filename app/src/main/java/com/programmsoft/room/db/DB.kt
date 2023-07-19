package com.programmsoft.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.programmsoft.room.dao.CurrencyDao
import com.programmsoft.room.entity.Currency


@Database(entities = [Currency::class], version = 1)

abstract class DB : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var instance: DB? = null

        @Synchronized
        fun getInstance(context: Context): DB {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DB::class.java, "currecies_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}