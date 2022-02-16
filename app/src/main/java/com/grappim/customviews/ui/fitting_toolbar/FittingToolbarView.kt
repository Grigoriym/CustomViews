package com.grappim.customviews.ui.fitting_toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class FittingToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val maxWidth = measuredWidth

        var takenWidth = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (takenWidth + child.measuredWidth < maxWidth) {
                child.layout(
                    takenWidth,
                    0,
                    takenWidth + child.measuredWidth,
                    height
                )
                takenWidth += child.measuredWidth
            } else {
                child.layout(0, 0, 0, 0)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var contentHeight = 0
        var contentWidth = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            contentWidth += child.measuredWidth
            contentHeight = max(contentHeight, child.measuredHeight)
        }

        val widthSize = resolveSizeSimplified(contentWidth, widthMeasureSpec)
        val heightSize = resolveSizeSimplified(contentHeight, heightMeasureSpec)
        setMeasuredDimension(widthSize, heightSize)
    }

    private fun resolveSizeSimplified(contentSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)

        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> if (contentSize < size) contentSize else size
            else -> contentSize
        }
    }
}