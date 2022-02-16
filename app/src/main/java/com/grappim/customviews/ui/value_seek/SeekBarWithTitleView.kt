package com.grappim.customviews.ui.value_seek

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.withStyledAttributes
import com.grappim.customviews.R
import com.grappim.customviews.utils.onProgressChanged

class SeekBarWithTitleView(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    companion object {
        private const val DEFAULT_SEEK_BAR_MAX = 100
        private const val DEFAULT_SEEK_BAR_PROGRESS = 20
    }

    private val tvTitle: TextView
    private val seekBar: SeekBar
    private val tvSeekBarValue: TextView

    private var _seekBarListener: ((value: Float) -> Unit)? = null
    private val seekBarListener
        get() = requireNotNull(_seekBarListener)

    var seekBarMax: Int = DEFAULT_SEEK_BAR_MAX
        set(value) {
            field = value
            seekBar.max = value
        }

    var seekBarProgress: Int = DEFAULT_SEEK_BAR_PROGRESS
        set(value) {
            field = value
            seekBar.progress = value
        }

    var titleText: String = ""
        set(value) {
            field = value
            tvTitle.text = value
        }

    init {
        inflate(context, R.layout.view_seek_bar_and_title, this)

        tvTitle = findViewById(R.id.tvTitle)
        seekBar = findViewById(R.id.seekBarAndTitle)
        tvSeekBarValue = findViewById(R.id.tvSeekBarValue)

        seekBar.onProgressChanged {
            tvSeekBarValue.text = "${it.toInt()}"
            seekBarListener(it)
        }

        context.withStyledAttributes(attrs, R.styleable.SeekBarWithTitleView) {
            titleText = getString(
                R.styleable.SeekBarWithTitleView_sbt_titleText
            ) ?: ""
            seekBarMax = getInteger(
                R.styleable.SeekBarWithTitleView_sbt_seekBarMax,
                DEFAULT_SEEK_BAR_MAX
            )
            seekBarProgress = getInteger(
                R.styleable.SeekBarWithTitleView_sbt_seekBarProgress,
                DEFAULT_SEEK_BAR_PROGRESS
            )

            tvSeekBarValue.text = "$seekBarProgress"
        }
    }

    fun setSeekBarCallback(callback: (value: Float) -> Unit) {
        _seekBarListener = callback
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _seekBarListener = null
    }
}