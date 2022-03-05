package com.grappim.customviews.ui.saved_state

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class SavedStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var someData: SomeData? = null

    init {
        println("asdf: $someData")
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        return ViewState(superState)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val viewState = state as? ViewState
        super.onRestoreInstanceState(viewState?.superState ?: state)
    }
}