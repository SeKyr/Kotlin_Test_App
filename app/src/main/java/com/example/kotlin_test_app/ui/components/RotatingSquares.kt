package com.example.kotlin_test_app.ui.components

import android.content.Context
import android.widget.LinearLayout
import kotlin.math.*

class RotatingSquares(context: Context, var n: Int, maxHeight: Int, maxWidth: Int) : LinearLayout(context) {

    init {
        this.clipChildren = false
        this.clipToPadding = false
        val squareSideLength = getOptimalSideLength(n, maxHeight.toDouble(), maxWidth.toDouble()).toInt()
        val rows = maxHeight / squareSideLength
        val columns = maxWidth / squareSideLength
        for (column in 0 until columns) {
            val columnLayout = LinearLayout(context);
            columnLayout.orientation = VERTICAL
            for (row in 0 until rows) {
                if (n > 0) {
                    columnLayout.addView(RotatingSquare(context, squareSideLength))
                    n--
                }
            }
            this.addView(columnLayout)
        }
    }

    private fun getOptimalSideLength(n: Int, width: Double, height: Double): Double {
        val sizePerSquare = width * height / n
        val squareSideLength: Double = sqrt(sizePerSquare)
        val smallerSideLength: Double = min(height, width)
        val biggerSideLength: Double = max(height, width)
        if (squareSideLength <= smallerSideLength && squareSideLength <= biggerSideLength / n) {
            return squareSideLength
        }
        var rows = 1
        var smallerSideCellLength = 0.0
        var biggerSideCellLength = 0.0
        var nextSmallerSideCellLength = 0.0
        while (biggerSideCellLength <= nextSmallerSideCellLength) {
            smallerSideCellLength = smallerSideLength / rows
            biggerSideCellLength = biggerSideLength / ceil(n / rows.toDouble())
            nextSmallerSideCellLength = smallerSideLength / (rows + 1)
            rows++
        }
        return min(smallerSideCellLength, biggerSideCellLength)
    }
}