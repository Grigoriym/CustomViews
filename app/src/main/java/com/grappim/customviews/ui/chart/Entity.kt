package com.grappim.customviews.ui.chart

import android.graphics.Canvas
import android.graphics.Paint

interface Entity {

    fun onFrame(frameTimeMillis: Long, chartSpeed: Int)
    fun draw(canvas: Canvas, paint: Paint)
}