package com.grappim.customviews.ui.music_equalizer

import androidx.lifecycle.ViewModel
import com.grappim.customviews.utils.tickerFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class MusicEqualizerViewModel(

) : ViewModel() {

    companion object {
        const val BARS_COUNT = 30
    }

    val equalizerBarItems: Flow<List<Float>> = tickerFlow(700.milliseconds)
        .map {
            (0 until BARS_COUNT).map {
                Random.nextFloat() * 0.8f
            }
        }
}