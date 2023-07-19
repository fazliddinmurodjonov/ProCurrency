package com.programmsoft.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {
    private const val NAME = "ProCurrencies"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreference: SharedPreferences
    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var downloadCurrency: Int?
        get() = sharedPreference.getInt("downloadCurrency", 0)
        set(value) = sharedPreference.edit {
            it.putInt("downloadCurrency", value!!)
        }

}