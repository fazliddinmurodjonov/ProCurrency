package com.programmsoft.retrofit

import com.programmsoft.models.currencies.CurrencyAPI
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("json")
    suspend fun getCurrencies(): Response<List<CurrencyAPI>>

}