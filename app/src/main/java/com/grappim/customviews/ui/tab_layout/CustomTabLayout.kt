package com.grappim.customviews.ui.tab_layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.grappim.customviews.R
import com.grappim.customviews.databinding.LayoutCustomTabLayoutBinding
import com.grappim.customviews.utils.extensions.color

class CustomTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutCustomTabLayoutBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    @ColorInt
    private val checkedBgColor = context.color(R.color.cv_color_surface)

    @ColorInt
    private val uncheckedBgColor = context.color(R.color.cv_color_surface_unchecked)

    var clicksListener: TabItemsClickListener? = null

    var currentTab: CustomTabItem = CustomTabItem.FIRST
        private set

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomTabLayout) {
            val tab1Title = getString(R.styleable.CustomTabLayout_ctl_tab_1_title)
            val tab2Title = getString(R.styleable.CustomTabLayout_ctl_tab_2_title)
            val tab3Title = getString(R.styleable.CustomTabLayout_ctl_tab_3_title)

            with(binding) {
                tvTitle1.text = tab1Title
                tvTitle2.text = tab2Title

                tabContainer1.setOnClickListener {
                    tabItemClicked(CustomTabItem.FIRST)
                    clicksListener?.onTabItemClicked(CustomTabItem.FIRST)
                }

                tabContainer2.setOnClickListener {
                    tabItemClicked(CustomTabItem.SECOND)
                    clicksListener?.onTabItemClicked(CustomTabItem.SECOND)
                }

                if (tab3Title != null) {
                    tabContainer3.isVisible = true
                    tvTitle3.text = tab3Title
                    tabContainer3.setOnClickListener {
                        tabItemClicked(CustomTabItem.THIRD)
                        clicksListener?.onTabItemClicked(CustomTabItem.THIRD)
                    }
                } else {
                    tabContainer3.isVisible = false
                }
            }
        }
    }

    fun changeUiOnPageChanged(position: Int) {
        val tabItem = CustomTabItem.values()[position]
        tabItemClicked(tabItem)
    }

    private fun tabItemClicked(tabItem: CustomTabItem) {
        currentTab = tabItem
        when (tabItem) {
            CustomTabItem.FIRST -> {
                binding.tabContainer1.setCardBackgroundColor(checkedBgColor)
                binding.tabContainer2.setCardBackgroundColor(uncheckedBgColor)
                binding.tabContainer3.setCardBackgroundColor(uncheckedBgColor)
            }
            CustomTabItem.SECOND -> {
                binding.tabContainer2.setCardBackgroundColor(checkedBgColor)
                binding.tabContainer1.setCardBackgroundColor(uncheckedBgColor)
                binding.tabContainer3.setCardBackgroundColor(uncheckedBgColor)
            }
            CustomTabItem.THIRD -> {
                binding.tabContainer3.setCardBackgroundColor(checkedBgColor)
                binding.tabContainer1.setCardBackgroundColor(uncheckedBgColor)
                binding.tabContainer2.setCardBackgroundColor(uncheckedBgColor)
            }
        }
    }

}