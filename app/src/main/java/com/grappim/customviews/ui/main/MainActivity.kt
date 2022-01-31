package com.grappim.customviews.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.grappim.customviews.databinding.ActivityMainBinding
import com.grappim.customviews.ui.base.BaseActivity
import com.grappim.customviews.ui.music_equalizer.MusicPlayerEqualizerFragment
import com.grappim.customviews.utils.FragmentScreens

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.screen.observe(this) {
            when (it) {
                is FragmentScreens.MusicPlayerEqualizer -> {
                    makeStandardFragmentTransaction<MusicPlayerEqualizerFragment, MainFragment>(
                        viewBinding.fragmentContainerView.id
                    )
                }
            }
        }
    }
}