package com.programmsoft.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Currency {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var cbPrice: String? = null
    var code: String? = null
    var buyPrice: String? = null
    var sellPrice: String? = null
    var title: String? = null
    var time: String? = null
    var date: String? = null

    constructor()
    constructor(
        cbPrice: String?,
        code: String?,
        buyPrice: String?,
        sellPrice: String?,
        title: String?,
        time: String?,
        date: String?,
    ) {
        this.cbPrice = cbPrice
        this.code = code
        this.buyPrice = buyPrice
        this.sellPrice = sellPrice
        this.title = title
        this.time = time
        this.date = date
    }


}