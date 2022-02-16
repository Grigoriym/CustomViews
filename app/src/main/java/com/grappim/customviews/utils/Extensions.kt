package com.grappim.customviews.utils

import android.content.res.Resources

val Number.toDp
    get() = this.toFloat() / Resources.getSystem().displayMetrics.density

val Number.toPx
    get() = this.toFloat() * Resources.getSystem().displayMetrics.density


