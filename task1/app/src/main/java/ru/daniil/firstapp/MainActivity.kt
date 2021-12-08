package ru.daniil.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val car = CarDto(1, "Lamba", "Dayday")
        outState.putParcelable(KEY, car)
        super.onSaveInstanceState(outState)
    }
    companion object {
        const val KEY = "car_key"
    }
}