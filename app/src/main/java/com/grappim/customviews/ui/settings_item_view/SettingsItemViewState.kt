package com.grappim.customviews.ui.settings_item_view

import android.os.Parcelable
import android.view.View
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsItemViewState(
    val superSavedState: Parcelable?,
    val isSwitchChecked: Boolean
) : View.BaseSavedState(superSavedState)
