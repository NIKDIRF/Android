package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val len = 18
    private val afterPoint = 3
    private var prev: Float = 0.0F
    private var clean = true
    private var afterOP = false
    private var ready = false
    private var action = ::inaction

    private fun getCurrent(): String = findViewById<TextView>(R.id.textView).text.toString()

    private fun setCurrent(str: String) {
        findViewById<TextView>(R.id.textView).text = str
    }

    fun addNum(view: View) {
        if (getCurrent().length >= len) return
        if (getCurrent()[0] == '0' || clean) {
            shift()
            clean = false
        }
        if (afterOP) ready = true
        setCurrent(getCurrent() + findViewById<Button>(view.id).text.toString())
    }

    private fun checkPoint(): Boolean = getCurrent().contains(".")

    fun addPoint(view: View) {
        if (checkPoint()) return
        if (clean) {
            shift()
            setCurrent("0")
            clean = false
        }
        setCurrent(getCurrent() + ".")
    }

    fun action(view: View) {

        if (afterOP && ready) return

        when (findViewById<Button>(view.id).text.toString()) {
            "+" -> action = ::plus
            "-" -> action = ::minus
            "/" -> action = ::divide
            "X" -> action = ::multiply
        }
        afterOP = true
        clean = true
    }

    private fun update() {
        setCurrent(rule(action()))
        prev = getCurrent().toFloat()
        afterOP = false
        ready = false
    }

    private fun shift() {
        prev = getCurrent().toFloat()
        setCurrent("")
    }

    private fun rule(num: String): String {
        var str = String.format("%.3f", num.toFloat())
        for (i in 0..afterPoint) {
            if (str.last() == '0' || str.last() == '.') str = str.dropLast(1)
        }
        return str
    }

    fun onClickEquals(view: View) {
        if (rule(action()).length > len) return
        update()
        action = ::inaction
        clean = true
    }


    fun setDefault(view: View) {
        prev = 0.0F
        afterOP = false
        clean = false
        ready = false
        setCurrent("0")
    }

    fun back(view: View) {
        setCurrent(getCurrent().dropLast(1))
        if (getCurrent() == "") setCurrent("0")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun plus(): String = (getCurrent().toFloat() + prev).toString()
    private fun minus(): String = (prev - getCurrent().toFloat()).toString()
    private fun divide(): String = (prev / getCurrent().toFloat()).toString()
    private fun multiply(): String = (prev * getCurrent().toFloat()).toString()
    private fun inaction(): String = getCurrent()
}