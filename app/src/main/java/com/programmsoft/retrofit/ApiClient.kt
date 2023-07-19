package com.programmsoft.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.programmsoft.utils.Components
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getCurrencies(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Components.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val serviceOfCurrencies: ApiService = getCurrencies().create(ApiService::class.java)

}