package com.programmsoft.models

import androidx.databinding.ObservableField

class ExchangeCurrencyModel {
    var exchangeCurrency: ObservableField<String> = ObservableField("0")

    constructor(exchangeCurrency: ObservableField<String>) {
        this.exchangeCurrency = exchangeCurrency
    }
}
