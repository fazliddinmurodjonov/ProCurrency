package com.programmsoft.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ScalePageTransformer(private val scaleFactor: Float) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val absPosition = abs(position)

        // Scale the page based on its position
        val scale = if (absPosition > 1) 1f else 1 - (1 - scaleFactor) * absPosition

        // Apply the scale transformation to the page
        page.scaleX = scale
        page.scaleY = scale
    }
}
