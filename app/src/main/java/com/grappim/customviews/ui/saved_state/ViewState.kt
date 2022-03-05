package com.grappim.customviews.ui.saved_state

import android.os.Parcelable
import android.view.View
import kotlinx.parcelize.Parcelize

@Parcelize
class ViewState(
    private val superSavedState: Parcelable?
) : View.BaseSavedState(superSavedState)