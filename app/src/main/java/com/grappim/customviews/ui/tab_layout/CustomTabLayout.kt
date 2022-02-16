package com.grappim.customviews.ui.tab_layout

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import com.grappim.customviews.R

class CustomTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TabLayout(context, attrs) {

    init {

    }

    fun createNewTab(
        tabTitle: String
    ): Tab {
        val tab = super.newTab()
        tab.setCustomView(R.layout.tab_item_view).apply {
            text = tabTitle
        }
        return tab
    }

    override fun newTab(): Tab {
        val tab = super.newTab()
        return if (tab.isSelected) {
            tab.setCustomView(R.layout.tab_item_view)
        } else {
            super.newTab()
        }
    }

}