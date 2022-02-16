package com.grappim.customviews.ui.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import io.reactivex.disposables.Disposable

class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    lateinit var dataProvider: DataProvider
    lateinit var chartRenderer: ChartRenderer

    private val backgroundPaint = Paint().apply {
        color = Color.YELLOW
        alpha = 20
    }
    private val stars = mutableListOf<SkyEntity>()

    private val radius = 20f
    private val skyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        alpha = 20
        strokeWidth = radius * 2f
        strokeCap = Paint.Cap.ROUND
    }

//    private var disposable:Disposable?

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
//        disposable=
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPaint(backgroundPaint)
        stars.forEach {
            it.draw(canvas, skyPaint)
        }
//        chartRenderer.draw(canvas,)
    }

    fun addValue(value: Double) {
    }

}