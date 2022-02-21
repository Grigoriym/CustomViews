package com.grappim.customviews.ui.value_seek

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.withStyledAttributes
import com.google.android.material.card.MaterialCardView
import com.grappim.customviews.R
import com.grappim.customviews.databinding.ViewSeekBarAndTitleBinding
import com.grappim.customviews.utils.delegate.viewProperty
import com.grappim.customviews.utils.onProgressChanged

class SeekBarWithTitleView(
    context: Context,
    attrs: AttributeSet
) : MaterialCardView(context, attrs) {

    companion object {
        private const val DEFAULT_SEEK_BAR_MAX = 100
        private const val DEFAULT_SEEK_BAR_PROGRESS = 20
    }

    private var _binding: ViewSeekBarAndTitleBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    var seekBarListener: ((value: Float) -> Unit)? = null

    var seekBarMax: Int by viewProperty(DEFAULT_SEEK_BAR_MAX)

    var seekBarProgress: Int by viewProperty(DEFAULT_SEEK_BAR_PROGRESS)

    var titleText: String by viewProperty("")

    init {
        _binding = ViewSeekBarAndTitleBinding.inflate(
            LayoutInflater.from(context),
            this
        )

        binding.seekBarAndTitle.onProgressChanged {
            seekBarProgress = it.toInt()
            seekBarListener?.invoke(it)
        }

        context.withStyledAttributes(attrs, R.styleable.SeekBarWithTitleView) {
            titleText = getString(
                R.styleable.SeekBarWithTitleView_sbt_titleText
            ) ?: ""
            seekBarMax = getInteger(
                R.styleable.SeekBarWithTitleView_sbt_seekBarMax,
                seekBarMax
            )
            seekBarProgress = getInteger(
                R.styleable.SeekBarWithTitleView_sbt_seekBarProgress,
                seekBarProgress
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        binding.seekBarAndTitle.max = seekBarMax
        binding.seekBarAndTitle.progress = seekBarProgress

        binding.tvSeekBarValue.text = "$seekBarProgress"

        binding.tvTitle.text = titleText

        super.onDraw(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        seekBarListener = null
        _binding = null
    }
}