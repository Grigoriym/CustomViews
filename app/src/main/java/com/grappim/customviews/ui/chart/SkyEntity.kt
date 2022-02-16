package com.grappim.customviews.ui.chart

import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import com.grappim.customviews.utils.toPx
import kotlin.random.Random

class SkyEntity(
    private val parent: View
) : Entity {

    private var x = -1f
    private var y = 0f
    private var radius = 0f
    private var speed = 0f

    init {
        generateNewValues(true)
    }

    private fun generateNewValues(initial: Boolean) {
        speed = 6.toPx * (4 + Random.nextFloat())
        radius = 3.toPx * (1 + Random.nextFloat())

        val dx = if (initial) 0 else 1
        x = parent.width * (dx + Random.nextFloat())
        y = parent.height * Random.nextFloat()
    }

    override fun onFrame(frameTimeMillis: Long, chartSpeed: Int) {
        if (x < -radius) {
            generateNewValues(false)
        }
        x -= speed * (frameTimeMillis / 1_000f) * chartSpeed
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawCircle(x, y, radius, paint)
    }
}