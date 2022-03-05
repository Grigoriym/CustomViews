package com.grappim.customviews.ui.tab_layout

interface TabItemsClickListener {

    fun onTabItemClicked(tabItem: CustomTabItem)

}

enum class CustomTabItem {
    FIRST,
    SECOND,
    THIRD;

    companion object {
        fun getItemOnBackPressed(item: CustomTabItem?): CustomTabItem =
            when (item) {
                SECOND -> FIRST
                THIRD -> SECOND
                else -> error("incorrect item: $item")
            }
    }
}