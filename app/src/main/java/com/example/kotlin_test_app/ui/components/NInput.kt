package com.example.kotlin_test_app.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.kotlin_test_app.R

class NInput @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val numberInput: EditText
    private val startButton: Button

    interface OnStartListener {
        fun onStart(number: Int)
    }

    private var onStartListener: OnStartListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.n_input, this, true)

        numberInput = findViewById(R.id.n_edit_text)
        startButton = findViewById(R.id.n_button_start)

        startButton.setOnClickListener {
            val number = numberInput.text.toString().toIntOrNull()
            if (number != null) {
                onStartListener?.onStart(number)
            }
        }
    }

    fun setOnStartListener(listener: OnStartListener) {
        onStartListener = listener
    }
}