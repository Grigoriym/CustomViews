package com.grappim.customviews.utils

import android.view.View
import android.widget.SeekBar
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

fun SeekBar.onProgressChanged(onProgressChanged: (Float) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            onProgressChanged(progress.toFloat())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }

    })
}

fun SwitchMaterial.onCheckChanged(
    onChecked: (isChecked: Boolean) -> Unit
) {
    setOnCheckedChangeListener { buttonView, isChecked ->
        onChecked(isChecked)
    }
}

fun View.showSnackbar(
    text: String,
    @BaseTransientBottomBar.Duration duration: Int
) {
    Snackbar.make(
        this,
        text,
        duration
    ).show()
}