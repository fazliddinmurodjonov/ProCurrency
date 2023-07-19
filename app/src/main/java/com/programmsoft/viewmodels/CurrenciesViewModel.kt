package com.programmsoft.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmsoft.repository.CurrenciesRepository
import kotlinx.coroutines.launch

class CurrenciesViewModel : ViewModel() {
    private val repository = CurrenciesRepository()

    fun getCurrencies(): LiveData<Boolean> {
        val response = MutableLiveData<Boolean>()
        viewModelScope.launch {
            repository.getCurrencies()
            response.value = true
        }
        return response
    }

}