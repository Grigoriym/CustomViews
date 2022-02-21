package com.grappim.customviews.ui.waveView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.grappim.customviews.databinding.FragmentWaveBinding
import com.grappim.customviews.ui.base.BaseFragment

class WaveFragment : BaseFragment<FragmentWaveBinding>(
    FragmentWaveBinding::inflate
) {

    private val viewModel by viewModels<WaveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(viewBinding) {
            waveView1.initData(viewModel.getWaveData())
            waveView2.initData(viewModel.getWaveData())
            waveView3.initData(viewModel.getWaveData())

            seekBarWaveView.seekBarListener = {
                waveView1.maxVolume = it.toInt()
            }
            seekBarWaveView.seekBarProgress = 30
            seekBarWaveView.seekBarMax = WaveViewModel.MAX_VOLUME
        }
    }
}