package com.grappim.customviews.ui.circular_text_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.InputFilter
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.grappim.customviews.R
import com.grappim.customviews.utils.delegate.viewProperty
import com.grappim.customviews.utils.dp
import com.grappim.customviews.utils.toPx

class CircularTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    companion object {
        private const val DEFAULT_CIRCLE_COLOR = Color.RED
        private const val DEFAULT_CIRCLE_STROKE_COLOR = Color.BLACK
        private const val DEFAULT_INDICATOR_COLOR = Color.CYAN

        private const val DEFAULT_STROKE_WIDTH = 4

        private const val DEFAULT_WIDTH = 0
        private const val DEFAULT_HEIGHT = 0

        private const val DEFAULT_STROKE_ENABLED = false
        private const val DEFAULT_INDICATOR_ENABLED = false
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = DEFAULT_CIRCLE_COLOR
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = DEFAULT_CIRCLE_STROKE_COLOR
        style = Paint.Style.STROKE
        strokeWidth = DEFAULT_STROKE_WIDTH.toFloat()
        strokeCap = Paint.Cap.ROUND
    }

    private val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = DEFAULT_INDICATOR_COLOR
        style = Paint.Style.FILL
    }

    @get:Px
    var strokeWidth: Int by viewProperty(DEFAULT_STROKE_WIDTH.dp)

    @get:ColorInt
    var strokeColor: Int by viewProperty(DEFAULT_CIRCLE_STROKE_COLOR)

    @get:ColorInt
    var circleColor: Int by viewProperty(DEFAULT_CIRCLE_COLOR)

    var strokeEnabled: Boolean by viewProperty(DEFAULT_STROKE_ENABLED)

    var indicatorEnabled: Boolean by viewProperty(DEFAULT_INDICATOR_ENABLED)
    var indicatorSizeCriteria: Float by viewProperty(6f)

    @get:ColorInt
    var indicatorColor: Int by viewProperty(DEFAULT_INDICATOR_COLOR)

    var textViewClickListener: (() -> Unit)? = null

    init {
        initTextView()

        context.withStyledAttributes(attrs, R.styleable.CircularTextView) {
            strokeColor = getColor(
                R.styleable.CircularTextView_ctv_strokeColor,
                strokeColor
            )

            circleColor = getColor(
                R.styleable.CircularTextView_ctv_circleColor,
                circleColor
            )

            strokeWidth = getDimensionPixelSize(
                R.styleable.CircularTextView_ctv_strokeWidth,
                strokeWidth
            )

            strokeEnabled = getBoolean(
                R.styleable.CircularTextView_ctv_strokeEnabled,
                strokeEnabled
            )
            indicatorEnabled = getBoolean(
                R.styleable.CircularTextView_ctv_indicatorEnabled,
                indicatorEnabled
            )
            indicatorColor = getColor(
                R.styleable.CircularTextView_ctv_indicatorColor,
                indicatorColor
            )
            indicatorSizeCriteria = getFloat(
                R.styleable.CircularTextView_civ_indicatorSizeCriteria,
                indicatorSizeCriteria
            )
        }
    }

    private fun initTextView() {
        gravity = Gravity.CENTER
        maxLines = 1
        filters = arrayOf(InputFilter.LengthFilter(3))
        setOnClickListener {
            textViewClickListener?.invoke()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSize(DEFAULT_WIDTH.toPx.toInt(), widthMeasureSpec)
        val height = resolveSize(DEFAULT_HEIGHT.toPx.toInt(), heightMeasureSpec)
        val textViewSize = width.coerceAtMost(height)
        setMeasuredDimension(textViewSize, textViewSize)
    }

    private fun applyPaintStyle() {
        if (strokeEnabled) {
            strokePaint.strokeWidth = strokeWidth.toFloat()
            strokePaint.color = strokeColor
        }

        circlePaint.color = circleColor

        if (indicatorEnabled) {
            indicatorPaint.color = indicatorColor
        }
    }

    override fun onDraw(canvas: Canvas) {
        applyPaintStyle()
        drawMainCircle(canvas)
        drawStrokeCircle(canvas)
        drawIndicator(canvas)
        super.onDraw(canvas)
    }

    private fun drawMainCircle(canvas: Canvas) {
        canvas.drawCircle(
            width / 2f,
            height / 2f,
            width / 2f,
            circlePaint
        )
    }

    private fun drawStrokeCircle(canvas: Canvas) {
        if (strokeEnabled) {
            canvas.drawCircle(
                width / 2f,
                height / 2f,
                width / 2f - strokeWidth / 2,
                strokePaint
            )
        }
    }

    private fun drawIndicator(canvas: Canvas) {
        if (indicatorEnabled) {
            val cx = width - (width / indicatorSizeCriteria)
            val cy = height - (height / indicatorSizeCriteria)
            canvas.drawCircle(
                cx,
                cy,
                width / indicatorSizeCriteria,
                indicatorPaint
            )
        }
    }
}