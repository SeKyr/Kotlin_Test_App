package com.example.kotlin_test_app.ui.ui_view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.kotlin_test_app.databinding.FragmentUiViewBinding
import com.example.kotlin_test_app.ui.components.NInput
import com.example.kotlin_test_app.ui.components.RotatingSquares

class UIViewFragment : Fragment() {

    private var _binding: FragmentUiViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUiViewBinding.inflate(inflater, container, false)
        val root: LinearLayout = binding.root


        val nInput: NInput = binding.nInput

        nInput.setOnStartListener(object : NInput.OnStartListener {
            override fun onStart(number: Int) {
                root.removeView(nInput)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.gravity = Gravity.CENTER
                val rotatingSquares = RotatingSquares(requireContext(), number, root.height, root.width)
                rotatingSquares.layoutParams = layoutParams
                root.addView(rotatingSquares)
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}