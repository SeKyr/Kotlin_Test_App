package com.example.kotlin_test_app.ui.components

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.Shape
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.kotlin_test_app.R

class RotatingSquare(context: Context, length: Int) : ConstraintLayout(context) {

    init {

        val square = View(context).apply {
            layoutParams = ViewGroup.LayoutParams(length, length)
            setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        }

        val shapeDrawable = ShapeDrawable()
        shapeDrawable.paint.style = Paint.Style.STROKE
        shapeDrawable.paint.strokeWidth = 1f
        shapeDrawable.paint.color = Color.BLACK

        square.foreground = shapeDrawable
        this.addView(square)

        val animation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animation.duration = 2000
        animation.repeatCount = Animation.INFINITE
        animation.interpolator = LinearInterpolator()
        this.animation = animation
        animation.start()
    }
}