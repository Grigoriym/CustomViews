package com.grappim.customviews.ui.progress_bar

import android.os.Bundle
import android.view.View
import com.grappim.customviews.databinding.FragmentCustomTabLayoutBinding
import com.grappim.customviews.ui.base.BaseFragment

class CustomTabLayoutFragment : BaseFragment<FragmentCustomTabLayoutBinding>(
    FragmentCustomTabLayoutBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val tabItems = listOf("one", "two", "three", "four", "five", "six")

        with(viewBinding) {
            tabItems.forEach {
                tabLayout.addTab(tabLayout.newTab().apply {
                    text = it
                })
            }
        }
    }
}