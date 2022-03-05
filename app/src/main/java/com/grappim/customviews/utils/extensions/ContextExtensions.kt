package com.grappim.customviews.utils.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.color(@ColorRes colorRes: Int): Int = ContextCompat.getColor(
    this,
    colorRes
)

fun Context.drawable(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)