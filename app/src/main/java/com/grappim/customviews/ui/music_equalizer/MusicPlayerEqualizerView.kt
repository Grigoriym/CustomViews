package com.grappim.customviews.ui.music_equalizer

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class MusicPlayerEqualizerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bars = emptyList<BarItem>()

    private val paint = Paint().apply {
        color = Color.RED
    }

    private val gradientColors =
        intArrayOf(
            Color.parseColor("#D16BA5"),
            Color.parseColor("#86A8E7"),
            Color.parseColor("#5FFBF1")
        )

    fun init(barsCount: Int) {
        bars = (0 until barsCount).map {
            BarItem().apply {
                doOnValueUpdateAction = { invalidate() }
            }
        }
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        val gap = height * 0.002f

        val barWidth = (width - gap * (bars.size - 1)) / bars.size.toFloat()
        val cy = height * 0.5f

        path.rewind()
        bars.forEachIndexed { index, barItem ->
            val left = (barWidth + gap) * index
            val barHeightHalf = (height * barItem.value * 0.5f).coerceAtLeast(barWidth)
            path.addRoundRect(
                left,
                cy - barHeightHalf,
                left + barWidth,
                cy + barHeightHalf,
                barWidth * 0.4f,
                barWidth * 0.4f,
                Path.Direction.CCW
            )
        }
        canvas.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val cy = height * 0.5f
        paint.shader = LinearGradient(
            0f,
            cy,
            0f,
            0f,
            gradientColors,
            null,
            Shader.TileMode.MIRROR
        )
    }

    fun updateBarsRelatively(data: List<Float>) {
        if (bars.size != data.size) {
            error("bars.size = ${bars.size} != data.size = ${data.size}")
        }

        bars.forEachIndexed { index, barItem ->
            barItem.updateValueRelatively(data[index])
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        bars.forEach { item ->
            item.onDetachedFromWindow()
        }
    }

}