package com.app.weather

import android.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import com.app.weather.components.WeatherItem
import com.app.weather.databinding.ActivityMainBinding
import com.app.weather.databinding.TopPanelBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        TopPanelBinding.bind(binding.root).switchTheme.setOnCheckedChangeListener { _, isChecked ->
            delegate.localNightMode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        }


    }

    override fun onResume() {

        binding.header.setMode(WeatherItem.ItemMode.HEADER, 0)

        for (i in 1..3) {
            val view = WeatherItem(context = this)
            val param = LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1F)
            view.layoutParams = param
            view.setMode(WeatherItem.ItemMode.INFO, i)
            binding.weatherInfo.addView(view)
        }

        for (i in 1..5) {
            val view = WeatherItem(context = this)
            val param = LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1F)
            view.layoutParams = param
            view.setMode(WeatherItem.ItemMode.WEEK, i)
            binding.week.addView(view)
        }

        for (i in 1..4) {
            val view = WeatherItem(context = this)
            val param = LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1F)
            view.layoutParams = param
            view.setMode(WeatherItem.ItemMode.MENU, i)
            binding.bottomMenu.addView(view)
        }

        super.onResume()
    }
}