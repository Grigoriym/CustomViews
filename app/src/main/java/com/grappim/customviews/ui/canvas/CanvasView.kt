package com.grappim.customviews.ui.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {

    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
    }
    private val circleStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 15f
    }
    private val ovalPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
    }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
        strokeWidth = 10f
    }
    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.RED)
        canvas.drawCircle(
            width / 4f,
            height / 4f,
            60f,
            circlePaint
        )
        canvas.drawCircle(
            width / 4f,
            height / 4f,
            80f,
            circleStrokePaint
        )

        canvas.drawOval(
            width / 4f,
            height / 2f,
            width.toFloat(),
            height.toFloat(),
            ovalPaint
        )
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f,
            height.toFloat(),
            linePaint
        )
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width.toFloat(),
            0f,
            linePaint
        )
        canvas.drawRect(
            width / 2f,
            0f,
            width.toFloat(),
            height / 2f,
            rectPaint
        )
        canvas.drawRoundRect(
            0f,
            height / 2f + ((height / 2f) / 2f),
            width / 4f,
            height.toFloat(),
            30f,
            30f,
            rectPaint
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(0, widthMeasureSpec)
        val height = resolveSize(0, heightMeasureSpec)
        val desiredSize = width.coerceAtMost(height)

        setMeasuredDimension(desiredSize, desiredSize)
    }
}