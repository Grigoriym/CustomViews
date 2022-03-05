package com.grappim.customviews.ui.progress_bar

import android.os.Bundle
import android.view.View
import com.grappim.customviews.databinding.FragmentProgressBarBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.utils.extensions.onProgressChanged

class ProgressBarFragment : BaseFragment<FragmentProgressBarBinding>(
    FragmentProgressBarBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            seekBarForegroundWidth.onProgressChanged { progress ->
                viewBinding.progressBar.progressBarWidth = progress
            }
            seekBarBackgroundWidth.onProgressChanged { progress ->
                viewBinding.progressBar.backgroundProgressBarWidth = progress
            }
        }
    }

}