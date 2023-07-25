package com.programmsoft.models

class CurrencySpinner {
    var id: Int? = null
    var code: String? = null
    var title: String? = null

    constructor()
    constructor(id: Int?, code: String?, title: String?) {
        this.id = id
        this.code = code
        this.title = title
    }

}