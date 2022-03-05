package com.grappim.customviews.ui.progress_bar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import com.grappim.customviews.R
import com.grappim.customviews.utils.calculateWidthAndHeight
import com.grappim.customviews.utils.extensions.toPx
import kotlin.math.min

class ProgressBarView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    companion object {
        private const val DEFAULT_PROGRESS_MAX_VALUE = 100f

        @ColorInt
        private const val DEFAULT_BACKGROUND_CIRCLE_COLOR = Color.GRAY

        @ColorInt
        private const val DEFAULT_FOREGROUND_CIRCLE_COLOR = Color.RED
    }

    private var progressAnimator: ValueAnimator? = null

    private val backgroundCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val rectF = RectF()

    private val foregroundCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    var progress: Float = 0f
        set(value) {
            field = if (progress <= progressMax) value else progressMax
            invalidate()
        }

    var progressMax: Float = DEFAULT_PROGRESS_MAX_VALUE
        set(value) {
            field = if (field >= 0) value else DEFAULT_PROGRESS_MAX_VALUE
            invalidate()
        }

    var progressBarWidth: Float = resources.getDimension(R.dimen.default_stroke_width)
        set(value) {
            field = value.toPx
            foregroundCirclePaint.strokeWidth = field
            requestLayout()
            invalidate()
        }
    var backgroundProgressBarWidth: Float =
        resources.getDimension(R.dimen.default_background_stroke_width)
        set(value) {
            field = value.toPx
            backgroundCirclePaint.strokeWidth = field
            requestLayout()
            invalidate()
        }

    private var progressIndeterminateMode: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    @ColorInt
    var backgroundCircleColor: Int = DEFAULT_BACKGROUND_CIRCLE_COLOR
        set(value) {
            field = value
            backgroundCirclePaint.color = field
            invalidate()
        }

    @ColorInt
    var foregroundCircleColor: Int = DEFAULT_FOREGROUND_CIRCLE_COLOR
        set(value) {
            field = value
            foregroundCirclePaint.color = field
            invalidate()
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.ProgressBarView) {
            backgroundCircleColor = getInt(
                R.styleable.ProgressBarView_gpb_backgroundProgressColor,
                DEFAULT_BACKGROUND_CIRCLE_COLOR
            )
            foregroundCircleColor = getInt(
                R.styleable.ProgressBarView_gpb_foregroundProgressColor,
                DEFAULT_FOREGROUND_CIRCLE_COLOR
            )

            progressBarWidth = getDimension(
                R.styleable.ProgressBarView_gpb_progressWidth,
                progressBarWidth
            )
            backgroundProgressBarWidth = getDimension(
                R.styleable.ProgressBarView_gpb_backgroundWidth,
                backgroundProgressBarWidth
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val (width, height) = calculateWidthAndHeight(widthMeasureSpec, heightMeasureSpec)
        val min = min(width, height)

        setMeasuredDimension(width, height)

        val highStroke = if (progressBarWidth > backgroundProgressBarWidth) {
            progressBarWidth
        } else {
            backgroundProgressBarWidth
        }

        rectF.set(
            0f + highStroke / 2,
            0f + highStroke / 2,
            min.toFloat() - highStroke / 2,
            min.toFloat() - highStroke / 2
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackgroundCircle(canvas)
        drawForegroundCircle(canvas)
    }

    private fun drawBackgroundCircle(canvas: Canvas) {
        canvas.drawOval(
            rectF,
            backgroundCirclePaint
        )
    }

    private fun drawForegroundCircle(canvas: Canvas) {
        canvas.drawOval(
            rectF,
            foregroundCirclePaint
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        progressAnimator?.cancel()
    }
}