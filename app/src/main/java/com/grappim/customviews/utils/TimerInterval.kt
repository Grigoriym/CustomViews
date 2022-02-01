package com.grappim.customviews.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * The idea was taken from here: https://stackoverflow.com/a/70776160/9822532
 */
class TimerInterval(
    val countDownInterval: Long = 1000L,
    runAtStart: Boolean = false,
    val onFinish: (() -> Unit)? = null,
    val onTick: ((Long) -> Unit)? = null
) {
    private var job: Job = Job()

    private val _playerMode = MutableStateFlow(PlayerMode.STOPPED)
    val playerMode = _playerMode.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        if (runAtStart) start()
    }

    fun start() {
        job.cancel()
        job = scope.launch(Dispatchers.IO) {
            _playerMode.value = PlayerMode.PLAYING
            while (isActive) {
                _playerMode.value = PlayerMode.PLAYING
                while (isActive) {
                    onTick?.invoke(countDownInterval)
                    delay(timeMillis = countDownInterval)
                }
            }
        }
    }

    fun pause() {
        job.cancel()
        _playerMode.value = PlayerMode.PAUSED
    }

    fun stop() {
        job.cancel()
        _playerMode.value = PlayerMode.STOPPED
    }

    enum class PlayerMode {
        PLAYING,
        PAUSED,
        STOPPED
    }
}