package ru.daniil.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.daniil.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY,binding.textViewResult.text.toString())
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val KEY = "result"
        const val DEFAULT = "…|"
        val NUMBERS = setOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    }

    fun buttonClicked(view: View) {
        val buttonChar = (view as Button).text
        if (binding.textViewResult.text == DEFAULT) {
            if (buttonChar in NUMBERS) {
                binding.textViewResult.text = buttonChar
            }
        } else {
            binding.textViewResult.append(buttonChar)
        }
    }

    fun clearResult(view: View) {
        binding.textViewResult.text = DEFAULT
    }



}