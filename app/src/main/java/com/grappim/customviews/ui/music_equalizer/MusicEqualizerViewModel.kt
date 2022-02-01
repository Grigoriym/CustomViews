package com.grappim.customviews.ui.music_equalizer

import androidx.lifecycle.ViewModel
import com.grappim.customviews.utils.TimerInterval
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MusicEqualizerViewModel(

) : ViewModel() {

    companion object {
        const val BARS_COUNT = 30
    }

    private val timer: TimerInterval = TimerInterval(
        countDownInterval = 700L,
        runAtStart = true,
        onTick = {
            _barItems.value = (0 until BARS_COUNT).map {
                Random.nextFloat() * 0.8f
            }
        }
    )


    private val _barItems = MutableStateFlow<List<Float>>(emptyList())
    val barItems: StateFlow<List<Float>>
        get() = _barItems.asStateFlow()

    fun stopEqualizer() {
        timer.stop()
    }

    fun startEqualizer() {
        timer.start()
    }

    fun pauseEqualizer() {
        timer.pause()
    }
}