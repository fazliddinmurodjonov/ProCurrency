package com.programmsoft.utils

import android.content.Context
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

object Functions {
    fun statusAndNavigationBars(context: Context,window: Window) {
        val decorView: View = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)
        wic.isAppearanceLightStatusBars =true
        wic.isAppearanceLightNavigationBars = true
       // window.statusBarColor = ContextCompat.getColor(context, R.color.background)
      //  window.navigationBarColor = ContextCompat.getColor(context, R.color.background)
    }

}