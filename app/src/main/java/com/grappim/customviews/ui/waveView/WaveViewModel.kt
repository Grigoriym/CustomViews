package com.grappim.customviews.ui.waveView

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class WaveViewModel(

) : ViewModel() {

    companion object {
        const val MAX_VOLUME = 100
        const val WAVE_LENGTH = 200
    }

    fun getWaveData(): IntArray {
        val data = IntArray(WAVE_LENGTH)
        val r = Random
        for (i in data.indices) {
            val value = r.nextInt(MAX_VOLUME + 1)
            data[i] = value
        }
        return data
    }
}