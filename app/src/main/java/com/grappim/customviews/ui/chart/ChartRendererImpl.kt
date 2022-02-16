package com.grappim.customviews.ui.chart

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.View

//class ChartRendererImpl(
//    private val parent: View,
//    private val offset: Float,
//    private val chartBackgroundPath: Path,
//    private val chartLinePath: Path,
//    private val fillPaint: Paint,
//    private val linePaint: Paint
//) : ChartRenderer {
//
//    override fun setValues(values: List<Double>) {
//    }
//
//    override fun addValue(value: Double) {
//    }
//
//    override fun onFrame(frameTimeMillis: Long, speed: Int) {
//    }
//
//    override fun draw(canvas: Canvas, clipWidth: Float, clipHeight: Float) {
//        canvas.save()
//
//        canvas.clipRect(0f, 0f, clipWidth, clipHeight)
//        canvas.translate(-offset, 0f)
//
//        canvas.drawPath(chartBackgroundPath, fillPaint)
//        canvas.drawPath(chartLinePath, linePaint)
//
//        canvas.restore()
//    }
//
//    private fun recreatePath() {
//        val bottom = parent.height - parent.paddingBottom.toFloat()
//        chartLinePath.rewind()
//        chartLinePath.moveTo(-batchWidth, bottom)
//        chartLinePath.rMoveTo(0f, -values.first().toChartY())
//
//        values.reduce { previous,current ->
//            val deltaHeight = previous.toChartY() - current.toChartY()
//
//            chartLinePath.rCubicTo((batchWidth * LINE_CURVE_FACTOR), 0f,
//            batchWidth - (batchWidth * LINE_CURVE_FACTOR), deltaHeight,
//            batchWidth, deltaHeight)
//
//            current
//        }
//
//        pathMeasure.setPath(chartLinePath, false)
//
//        chartBackgroundPath.set(chartLinePath)
//        chartBackgroundPath.rLineTo(0f, parent.height.toFloat())
//        chartBackgroundPath.rLineTo(-batchWidth * (values.size - 1), 0f)
//        chartBackgroundPath.close()
//    }
//
//    private fun findYOnChart(x: Float): Float {
//        val coordinates = floatArrayOf(0f, 0f)
//        var rangeStart = 0f
//        var rangeEnd = pathMeasure.length
//        var midDistance: Float
//
//        while (rangeStart.farFrom(rangeEnd, POINT_LOOKUP_TOLERANCE)) {
//            midDistance = rangeStart + (rangeEnd - rangeStart) / 2
//            pathMeasure.getPosTan(midDistance, coordinates, null)
//            when {
//                coordinates[0].closeTo(x, POINT_LOOKUP_TOLERANCE) -> return coordinates[1]
//                coordinates[0] < x -> rangeStart = midDistance
//                else -> rangeEnd = midDistance
//            }
//        }
//
//        return coordinates[1]
//    }
//
//    override fun getChartItemByScreenX(x: Float): ChartItem {
//    }
//}