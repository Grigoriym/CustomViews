package com.grappim.customviews.ui.saved_state

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.grappim.customviews.databinding.FragmentSavedStateBinding
import com.grappim.customviews.ui.base.BaseFragment
import com.grappim.customviews.utils.extensions.showSnackbar

class SavedStateFragment : BaseFragment<FragmentSavedStateBinding>(
    FragmentSavedStateBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            vSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                view?.showSnackbar("$isChecked", Snackbar.LENGTH_SHORT)
            }
        }
    }
}