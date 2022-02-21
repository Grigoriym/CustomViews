package com.grappim.customviews.ui.fitting_toolbar

import android.os.Bundle
import android.view.View
import com.grappim.customviews.databinding.FragmentFittingToolbarBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.utils.dp

class FittingToolbarFragment : BaseFragment<FragmentFittingToolbarBinding>(
    FragmentFittingToolbarBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            seekBar.seekBarMax = resources.displayMetrics.densityDpi
            seekBar.seekBarListener = {
                viewBinding.fittingToolbar1.layoutParams.width = it.toInt().dp
                viewBinding.fittingToolbar1.requestLayout()
            }
        }
    }

}