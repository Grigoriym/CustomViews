package com.grappim.customviews.ui.circular_text_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.grappim.customviews.utils.toDp
import com.grappim.customviews.utils.toPx

class CircularTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    companion object {
        private const val DEFAULT_CIRCLE_COLOR = Color.RED
        private const val DEFAULT_CIRCLE_STROKE_COLOR = Color.BLACK

        private const val DEFAULT_STROKE_WIDTH = 10f
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = DEFAULT_CIRCLE_COLOR
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = DEFAULT_CIRCLE_STROKE_COLOR
//        style = Paint.Style.STROKE
    }

    var circleColor: Int = DEFAULT_CIRCLE_COLOR
        set(value) {
            field = value
            circlePaint.color = value
        }

    var strokeWidth: Float = DEFAULT_STROKE_WIDTH
        set(value) {
            field = value.toDp
        }

    init {

    }

    override fun onDraw(canvas: Canvas) {
        val diameter = if (height > width) height else width
        println("asd circle: height: $height px, ${height.toDp} dp, width: $width px, ${width.toDp} dp")
        val radius = (diameter / 2).toFloat()

        canvas.drawCircle(
            radius,
            radius,
            radius,
            circlePaint
        )

        canvas.drawCircle(
            radius,
            radius,
            radius - strokeWidth,
            strokePaint
        )

        super.onDraw(canvas)
    }
}