package com.example.kotlin_test_app.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlin_test_app.R

class CalculationWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var algorithmImplementation: ((n: Int) -> Long)? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.calcuation_widget, this, true)

        val nInput: NInput = findViewById(R.id.n_input)
        val textAlgorithmName: TextView = findViewById(R.id.text_algorithm_name)
        val textNAndExecutionTime: TextView = findViewById(R.id.text_n_and_execution_time)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CalculationWidget,
            0, 0).apply {

            try {
                textAlgorithmName.text = getString(R.styleable.CalculationWidget_algorithmName) + ": "
            } finally {
                recycle()
            }
        }

        nInput.setOnStartListener(object : NInput.OnStartListener {
            override fun onStart(number: Int) {
                textNAndExecutionTime.text = "${algorithmImplementation?.let { it(number) }}μs; n = $number"
            }
        })
    }

    fun setAlgorithmImplementation(impl: ((Int) -> Long)) {
        algorithmImplementation = impl
    }
}