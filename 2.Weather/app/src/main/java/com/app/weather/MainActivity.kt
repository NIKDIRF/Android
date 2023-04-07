package com.app.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.app.weather.databinding.ActivityMainBinding
import com.app.weather.databinding.TopPanelBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        TopPanelBinding.bind(binding.root).switchTheme.setOnCheckedChangeListener { buttonView, isChecked ->
            delegate.localNightMode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        }
    }
}