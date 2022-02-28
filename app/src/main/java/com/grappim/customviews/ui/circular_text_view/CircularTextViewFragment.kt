package com.grappim.customviews.ui.circular_text_view

import android.os.Bundle
import android.view.View
import com.grappim.customviews.databinding.FragmentCircularTextViewBinding
import com.grappim.customviews.ui.base.BaseFragment

class CircularTextViewFragment : BaseFragment<FragmentCircularTextViewBinding>(
    FragmentCircularTextViewBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {

        }
    }
}