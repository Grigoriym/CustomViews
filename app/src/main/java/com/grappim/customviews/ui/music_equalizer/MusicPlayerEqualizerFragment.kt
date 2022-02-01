package com.grappim.customviews.ui.music_equalizer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.grappim.customviews.databinding.FragmentMusicPlayerEqualizerBinding
import com.grappim.customviews.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MusicPlayerEqualizerFragment : BaseFragment<FragmentMusicPlayerEqualizerBinding>(
    FragmentMusicPlayerEqualizerBinding::inflate
) {

    private val viewModel: MusicEqualizerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .barItems
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .filter {
                    it.isNotEmpty()
                }
                .collectLatest {
                    println("asd data $it")
                    viewBinding.musicPlayer.updateBarsRelatively(it)
                }
        }
    }

    private fun initViews() {
        with(viewBinding) {
            musicPlayer.init(MusicEqualizerViewModel.BARS_COUNT)

            btnStart.setOnClickListener {
                viewModel.startEqualizer()
            }
            btnStop.setOnClickListener {
                viewModel.stopEqualizer()
            }
            btnPause.setOnClickListener {
                viewModel.pauseEqualizer()
            }
        }
    }
}