package com.grappim.customviews.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.grappim.customviews.databinding.ActivityMainBinding
import com.grappim.customviews.ui.base.BaseActivity
import com.grappim.customviews.ui.canvas.CanvasFragment
import com.grappim.customviews.ui.circular_text_view.CircularTextViewFragment
import com.grappim.customviews.ui.fitting_toolbar.FittingToolbarFragment
import com.grappim.customviews.ui.music_equalizer.MusicPlayerEqualizerFragment
import com.grappim.customviews.ui.progress_bar.ProgressBarFragment
import com.grappim.customviews.ui.saved_state.SavedStateFragment
import com.grappim.customviews.ui.settings_item_view.SettingsFragment
import com.grappim.customviews.ui.tab_layout.CustomTabLayoutFragment
import com.grappim.customviews.ui.waveView.WaveFragment
import com.grappim.customviews.utils.FragmentScreens

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val viewModel by viewModels<MainViewModel>()

    override val containerViewId: Int
        get() = viewBinding.fragmentContainerView.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.screen.observe(this) {
            when (it) {
                is FragmentScreens.MusicPlayerEqualizer -> {
                    makeStandardFragmentTransaction<MusicPlayerEqualizerFragment, MainFragment>()
                }
                is FragmentScreens.FittingToolbar -> {
                    makeStandardFragmentTransaction<FittingToolbarFragment, MainFragment>()
                }
                is FragmentScreens.SettingsItems -> {
                    makeStandardFragmentTransaction<SettingsFragment, MainFragment>()
                }
                is FragmentScreens.ProgressBar -> {
                    makeStandardFragmentTransaction<ProgressBarFragment, MainFragment>()
                }
                is FragmentScreens.WaveView -> {
                    makeStandardFragmentTransaction<WaveFragment, MainFragment>()
                }
                is FragmentScreens.CircularTextView -> {
                    makeStandardFragmentTransaction<CircularTextViewFragment, MainFragment>()
                }
                is FragmentScreens.CustomTabLayout -> {
                    makeStandardFragmentTransaction<CustomTabLayoutFragment, MainFragment>()
                }
                is FragmentScreens.CanvasView -> {
                    makeStandardFragmentTransaction<CanvasFragment, MainFragment>()
                }
                is FragmentScreens.SavedStateView -> {
                    makeStandardFragmentTransaction<SavedStateFragment, MainFragment>()
                }
            }
        }
    }
}