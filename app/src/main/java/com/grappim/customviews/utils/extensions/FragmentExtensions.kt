package com.grappim.customviews.utils.extensions

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.color(@ColorRes id: Int): Int =
    ContextCompat.getColor(
        requireContext(),
        id
    )