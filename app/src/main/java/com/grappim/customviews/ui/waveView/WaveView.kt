package com.grappim.customviews.ui.waveView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.withStyledAttributes
import com.grappim.customviews.R
import com.grappim.customviews.utils.LinearInterpolation

class WaveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    companion object {
        private const val DEFAULT_ITEM_WIDTH_DP = 2
        private const val DEFAULT_ITEM_COLOR = Color.BLACK
    }

    var maxVolume: Int = WaveViewModel.MAX_VOLUME
        set(value) {
            field = value
            invalidate()
        }

    private val wavePath = Path()
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val itemWidth: Int

    private val originalData = mutableListOf<Int>()
    private var measuredData: IntArray? = null

    init {
        val displayMetrics = context.resources.displayMetrics
        var itemWidthFromAttr = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            DEFAULT_ITEM_WIDTH_DP.toFloat(),
            displayMetrics
        ) + 0.5f).toInt()
        var itemColorFromAttr = DEFAULT_ITEM_COLOR

        context.withStyledAttributes(attrs, R.styleable.WaveView) {
            itemColorFromAttr = getColor(R.styleable.WaveView_itemColor, itemColorFromAttr)
            itemWidthFromAttr = getDimensionPixelSize(
                R.styleable.WaveView_itemWidth,
                itemWidthFromAttr
            )

            maxVolume = getInteger(
                R.styleable.WaveView_wv_maxVolume,
                maxVolume
            )

        }

        itemWidth = itemWidthFromAttr
        linePaint.apply {
            color = itemColorFromAttr
            strokeWidth = itemWidthFromAttr.toFloat()
        }
    }

    fun initData(data: IntArray) {
        originalData.clear()
        originalData.addAll(data.toList())
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        val itemCount = (width - paddingStart - paddingEnd + itemWidth) / (itemWidth * 2)
        if (originalData.isNotEmpty()) {
            measuredData = LinearInterpolation.interpolateArray(
                originalData.toIntArray(),
                itemCount
            )
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val measuredData = measuredData ?: return

        wavePath.reset()

        val measuredHeight = measuredHeight - paddingTop - paddingBottom
        var currentX = paddingStart

        measuredData.forEach { data ->
            val height: Float = (data.toFloat() / maxVolume) * measuredHeight
            val startY: Float = (measuredHeight.toFloat() / 2f) - (height / 2f) + paddingTop
            val endY = startY + height

            wavePath.moveTo(currentX.toFloat(), startY)
            wavePath.lineTo(currentX.toFloat(), endY)
            currentX += itemWidth * 2
        }
        canvas.drawPath(wavePath, linePaint)
    }
}