package com.grappim.customviews.ui.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.grappim.customviews.databinding.FragmentChartBinding
import com.grappim.customviews.ui.base.BaseFragment

class ChartFragment : BaseFragment<FragmentChartBinding>(
    FragmentChartBinding::inflate
) {

    private val viewModel by viewModels<ChartViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {

    }

    private fun observeViewModel() {

    }
}