package com.grappim.customviews.utils.extensions

import android.content.res.Resources
import android.content.res.TypedArray
import android.util.TypedValue
import kotlin.math.roundToInt

val Number.toPx: Float
    get() = if (this == 0) {
        0f
    } else {
        this.toFloat() * Resources.getSystem().displayMetrics.density
    }

internal val Int.dp: Int
    @JvmSynthetic inline get() = if (this == 0) {
        0
    } else {
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).roundToInt()
    }

internal inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T): T {
    return getInt(index, -1).let {
        if (it >= 0) enumValues<T>()[it] else default
    }
}