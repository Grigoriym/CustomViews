package com.grappim.customviews.ui.canvas

import android.os.Bundle
import android.view.View
import com.grappim.customviews.databinding.FragmentCanvasBinding
import com.grappim.customviews.ui.base.BaseFragment

class CanvasFragment : BaseFragment<FragmentCanvasBinding>(
    FragmentCanvasBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

    }
}