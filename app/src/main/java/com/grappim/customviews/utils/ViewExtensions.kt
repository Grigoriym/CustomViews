package com.grappim.customviews.utils

import android.widget.SeekBar
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