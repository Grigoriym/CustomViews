package com.grappim.customviews.ui.chart

import android.graphics.Canvas

interface ChartRenderer {
    fun setValues(values: List<Double>)
    fun addValue(value: Double)
    fun onFrame(frameTimeMillis: Long, speed: Int)
    fun draw(canvas: Canvas, clipWidth: Float, clipHeight: Float)
    fun getChartItemByScreenX(x: Float): ChartItem
}