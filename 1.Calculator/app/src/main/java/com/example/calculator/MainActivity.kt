package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tool = CalcTools()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button0.setOnClickListener(::numberClicked)
        binding.button1.setOnClickListener(::numberClicked)
        binding.button2.setOnClickListener(::numberClicked)
        binding.button3.setOnClickListener(::numberClicked)
        binding.button4.setOnClickListener(::numberClicked)
        binding.button5.setOnClickListener(::numberClicked)
        binding.button6.setOnClickListener(::numberClicked)
        binding.button7.setOnClickListener(::numberClicked)
        binding.button8.setOnClickListener(::numberClicked)
        binding.button9.setOnClickListener(::numberClicked)
        binding.buttonDivide.setOnClickListener(::signClicked)
        binding.buttonMinus.setOnClickListener(::signClicked)
        binding.buttonMultiply.setOnClickListener(::signClicked)
        binding.buttonPlus.setOnClickListener(::signClicked)
        binding.buttonPoint.setOnClickListener(::signClicked)
        binding.buttonDivide.setOnClickListener(::signClicked)
        binding.buttonAC.setOnClickListener(::clear)
        binding.buttonBack.setOnClickListener(::cancel)
        binding.buttonEquals.setOnClickListener(::calculate)

    }

    private fun numberClicked(view: View) {
        val num = (view as Button).text.last()
        tool.addNum(num)
        binding.result.text = tool.text
    }

    private fun signClicked(view: View) {
        val sign = (view as Button).text.last()
        tool.addSign(sign)
        binding.result.text = tool.text
    }

    private fun cancel(view: View) {
        tool.cancelOne()
        binding.result.text = tool.text
    }

    private fun clear(view: View) {
        tool.clearAll()
        binding.result.text = tool.text
    }

    private fun calculate(view: View) {
        binding.result.text = tool.calculate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("result", binding.result.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.result.text = savedInstanceState.getString("result")
    }

}