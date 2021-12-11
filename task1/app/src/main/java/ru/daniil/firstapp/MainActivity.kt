package ru.daniil.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import net.objecthunter.exp4j.ExpressionBuilder
import ru.daniil.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var zeroControl = false
    private var commaControl = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY, binding.textViewResult.text.toString())
        super.onSaveInstanceState(outState)
    }

    fun buttonClickedForFirsts(view: View) {
        removeError()
        val buttonText = (view as Button).text
        if (!binding.textViewResult.text.isEmpty() && binding.textViewResult.text.last() == '0' && !zeroControl && binding.textViewResult.text.length >= MAXLEN) {
            binding.textViewResult.text = binding.textViewResult.text.subSequence(0, binding.textViewResult.text.length - 1)
        }
        if (binding.textViewResult.text.length < MAXLEN) {
            binding.textViewResult.append(buttonText)
        }
        zeroControl = true
    }

    fun zeroButtonClicked(view: View) {
        removeError()
        val buttonText = (view as Button).text
        if ((zeroControl || binding.textViewResult.text.isEmpty() || binding.textViewResult.text.last() != '0') && binding.textViewResult.text.length < MAXLEN) {
            binding.textViewResult.append(buttonText)
        }
    }

    fun specialButtonClicked(view: View) {
        removeError()
        val buttonText = (view as Button).text
        if (binding.textViewResult.text.isEmpty() && buttonText == "-") {
            binding.textViewResult.append(buttonText)
        }
        if (!binding.textViewResult.text.isEmpty()) {
            if (binding.textViewResult.text.last() in SPECIAL) {
                binding.textViewResult.text = binding.textViewResult.text.subSequence(0, binding.textViewResult.text.length - 1)
            }
            if (binding.textViewResult.text.length < MAXLEN && (buttonText == "," && !commaControl || buttonText != ",")) {
                    binding.textViewResult.append(buttonText)
            }
            zeroControl = buttonText == ","
            commaControl = zeroControl
        }
    }

    fun piButtonClicked(view: View) {
        removeError()
        val buttonText: CharSequence = PI
        if (binding.textViewResult.text.length < MAXLEN - 2 && !commaControl) {
            if (!binding.textViewResult.text.isEmpty() && binding.textViewResult.text.last() == '0') {
                binding.textViewResult.text = binding.textViewResult.text.subSequence(0, binding.textViewResult.text.length - 1)
            }
            binding.textViewResult.append(buttonText)
            commaControl = true
        }
    }


    fun clearResult(view: View) {
        binding.textViewResult.text = DEFAULT
        commaControl = false
        zeroControl = false
    }

    fun cancelOne() {
        removeError()
        if (!binding.textViewResult.text.isEmpty()) {
            val last = binding.textViewResult.text.last()
            binding.textViewResult.text = binding.textViewResult.text.subSequence(0, binding.textViewResult.text.length - 1)
            if (last == ',') {
                commaControl = false
                zeroControl = binding.textViewResult.text.last() in NUMBERS
            }
        }
    }

    private fun removeError() {
        if (!binding.textViewResult.text.isEmpty() &&  binding.textViewResult.text.last() == '!') {
            binding.textViewResult.text = DEFAULT
            commaControl = false
            zeroControl = false
        }
    }

    private fun symbolControl(): Boolean {
        val text =binding.textViewResult.text
        var i: Int = text.length - 1
        while (i >= 0) {
            if (text[i] in NUMBERS) {
                zeroControl = true
            }
            if (text[i] == ',') {
                return true
            }
            if (text[i] in SPECIAL) {
                return false
            }
            i--
        }
        return false
    }

    fun calculated(view: View) {
        try {
            binding.textViewResult.text = ExpressionBuilder(binding.textViewResult.text.toString().replace(',', '.').replace('×', '*')).build().evaluate().toString().replace('.', ',')
            commaControl =  symbolControl()
        } catch (e : ArithmeticException) {
            binding.textViewResult.text = getString(R.string.division_by_zero)
        } catch (e : NumberFormatException) {
            binding.textViewResult.text = getString(R.string.incorrect_number_format)
        } catch (e : IllegalArgumentException) {
            binding.textViewResult.text = getString(R.string.invalid_expression)
        } catch (e : Exception) {
            binding.textViewResult.text = getString(R.string.error)
        }
    }


    companion object {
        const val KEY = "result"
        const val DEFAULT = ""
        const val PI = "3,14"
        const val MAXLEN = 20
        val NUMBERS = setOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
        val SPECIAL = setOf('/', '×', '+', '-', ',')

    }

}