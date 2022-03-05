package com.grappim.customviews.ui.tab_layout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grappim.customviews.ui.tab_layout.tabs.TabFirstFragment
import com.grappim.customviews.ui.tab_layout.tabs.TabSecondFragment
import com.grappim.customviews.ui.tab_layout.tabs.TabThirdFragment

class CustomTabLayoutViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = CustomTabItem.values().size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            CustomTabItem.FIRST.ordinal -> TabFirstFragment()
            CustomTabItem.SECOND.ordinal -> TabSecondFragment()
            CustomTabItem.THIRD.ordinal -> TabThirdFragment()
            else -> error("incorrect position: $position when itemCount: $itemCount")
        }
}