package com.grappim.customviews.ui.circular_text_view

import android.os.Bundle
import android.view.View
import com.grappim.customviews.R
import com.grappim.customviews.databinding.FragmentCircularTextViewBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.utils.color

class CircularTextViewFragment : BaseFragment<FragmentCircularTextViewBinding>(
    FragmentCircularTextViewBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            ctvFirst.circleColor = color(R.color.cv_color_golden_tainoi)
        }
    }
}