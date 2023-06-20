package com.example.kotlin_test_app.ui.calculation_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_test_app.databinding.FragmentCalculationViewBinding
import com.example.kotlin_test_app.ui.components.CalculationWidget
import kotlin.math.sin
import kotlin.system.measureNanoTime

class CalculationViewFragment : Fragment() {

    private var _binding: FragmentCalculationViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculationViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val fibCalculationWidget: CalculationWidget = binding.fibCalculationWidget
        val matrixCalculationWidget: CalculationWidget = binding.matrixCalculationWidget
        val binaryCalculationWidget: CalculationWidget = binding.binaryCalculationWidget
        val arrayCalculationWidget: CalculationWidget = binding.arrayCalculationWidget

        fibCalculationWidget.setAlgorithmImplementation { n -> fibonacciAlgorithm(n) }
        matrixCalculationWidget.setAlgorithmImplementation { n -> matrixMultiplicationAlgorithm(n) }
        binaryCalculationWidget.setAlgorithmImplementation { n -> binarySearchTreeAlgorithm(n) }
        arrayCalculationWidget.setAlgorithmImplementation { n -> reverseArrayAlgorithm(n) }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fibonacciAlgorithm(n: Int): Long {
        return measureNanoTime{ fib(n) } / 1000;
    }

    private fun fib(n: Int): Int {
        if (n <= 2) {
            return 1;
        }
        return fib(n - 1) + fib( n - 2 ) ;
    }

    private fun matrixMultiplicationAlgorithm(n: Int): Long {
        val firstMatrix: List<List<Int>> =  List(n) { row -> List(n) { column -> (row + 1) * (column + 1) } }
        val secondMatrix: List<List<Int>> = List(n) { row -> List(n) { column -> (row + 1) + (column + 1) } };
        val resultMatrix: List<MutableList<Int>> = List(n) { List(n) { 0 }.toMutableList() }

        return measureNanoTime {
            for (row in 0 until n) {
                for (col in 0 until n) {
                    var cell = 0
                    for (h in 0 until n) {
                        cell += firstMatrix[row][h] * secondMatrix[h][col]
                    }
                    resultMatrix[row][col] = cell
                }
            }
        } / 1000
    }

    private fun binarySearchTreeAlgorithm(n: Int): Long {
        val data: List<Int> = List(n) { ((sin(it.toDouble()) - sin((it + 1).toDouble())) * 1000).toInt() }
        return measureNanoTime {
            for (element in data) {
                insert(element);
            }
        } / 1000
    }

    class Node(var data: Int, var left: Node?, var right: Node?)

    private var root: Node? = null;

    private fun insert(data: Int) {
        val newNode = Node(data, null, null)
        if(root == null) {
            root = newNode;
            return;
        } else {
            var current = root;

            while (current != null) {
                if(data < current.data) {
                    if(current.left != null) {
                        current = current.left;
                    } else {
                        current.left = newNode;
                        return;
                    }
                } else {
                    if(current.right != null) {
                        current = current.right;
                    } else {
                        current.right = newNode;
                        return;
                    }
                }
            }
            return;
        }
    }

    private fun reverseArrayAlgorithm(n: Int): Long {
        val array: List<Int> = List(n) { it }
        return measureNanoTime {
            reverse(array);
        } / 1000
    }

    private fun reverse(toReverse: List<Int>): MutableList<Int> {
        val toReverseMutable = toReverse.toMutableList()
        if(toReverse.isEmpty()) {
            return emptyList<Int>().toMutableList();
        }
        val first = toReverseMutable.removeAt(0);
        val reversed = reverse(toReverseMutable);
        reversed.add(first);
        return reversed;
    }
}