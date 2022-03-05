package com.grappim.customviews.ui.tab_layout

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.grappim.customviews.databinding.FragmentCustomTabLayoutBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.ui.tab_layout.tabs.CustomTabLayoutViewModel

class CustomTabLayoutFragment : BaseFragment<FragmentCustomTabLayoutBinding>(
    FragmentCustomTabLayoutBinding::inflate
), TabItemsClickListener {

    private val viewModel by viewModels<CustomTabLayoutViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initOnBackPressedDispatcher()
        observeViewModel()
    }

    private fun initOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (viewBinding.viewPagerTabLayout.currentItem == 0) {
                        parentFragmentManager.popBackStack()
                    } else {
                        viewModel.onBackPressed()
                    }
                }
            }
        )
    }

    private fun initViews() {
        with(viewBinding) {
            viewPagerTabLayout.adapter =
                CustomTabLayoutViewPagerAdapter(this@CustomTabLayoutFragment)
            viewPagerTabLayout.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewBinding.tabLayout1.changeUiOnPageChanged(position)
                }
            })
            tabLayout1.clicksListener = this@CustomTabLayoutFragment
        }
    }

    private fun observeViewModel() {
        viewModel.currentScreen.observe(viewLifecycleOwner) {
            viewBinding.viewPagerTabLayout.currentItem = it.ordinal
        }
    }

    override fun onTabItemClicked(tabItem: CustomTabItem) {
        viewModel.setNextScreen(tabItem)
    }
}