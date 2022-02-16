package com.grappim.customviews.utils

import android.view.View
import kotlin.math.min

internal fun calculateWidthAndHeight(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
): Pair<Int, Int> {
    val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
    val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

    val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
    val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)

    val min = min(widthSize, heightSize)
    return Pair(min, min)
}