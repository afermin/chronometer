package com.rhino.chronometer.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 11/23/17.
 */
class CustomSquareLayout : FrameLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)

        if (width <  height) {
            height = width
        } else {
            width = height
        }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
    }
}