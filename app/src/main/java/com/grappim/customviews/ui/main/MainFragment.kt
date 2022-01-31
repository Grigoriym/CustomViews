package com.grappim.customviews.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.grappim.customviews.databinding.FragmentMainBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.utils.FragmentScreens

class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            btnMusicEqualizer.setOnClickListener{
                mainViewModel.moveToScreen(FragmentScreens.MusicPlayerEqualizer())
            }
        }
    }

}