package com.grappim.customviews.ui.settings_item_view

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.grappim.customviews.databinding.FragmentSettingsBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.utils.extensions.showSnackbar

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(viewBinding) {
            settingsFirst.switchListener = {
                viewBinding.root.showSnackbar(
                    "$it",
                    Snackbar.LENGTH_SHORT
                )
            }
            seekBarTitleSize.seekBarListener = {
                settingsFirst.titleTextSize = it
            }
            btnSetTitleText.setOnClickListener {
                settingsFirst.setTitleText(etTitleText.text.toString())
                etTitleText.setText("")
            }

            btnBold.setOnClickListener {
                settingsFirst.isTitleBold = !settingsFirst.isTitleBold
            }
            btnItalic.setOnClickListener {
                settingsFirst.isTitleItalic = !settingsFirst.isTitleItalic
            }
            btnNormal.setOnClickListener {
                settingsFirst.setTitleTextNormal()
            }
        }
    }
}