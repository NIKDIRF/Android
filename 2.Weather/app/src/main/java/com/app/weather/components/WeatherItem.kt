package com.app.weather.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.app.weather.databinding.WeatherItemBinding
import com.app.weather.R

@SuppressLint("ViewConstructor")
class WeatherItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs)  {

    private var binding: WeatherItemBinding =
        WeatherItemBinding.inflate(LayoutInflater.from(context), this)



    private lateinit var data: MutableMap<String, Int>
    private lateinit var iconsTypeface: Typeface

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL

        iconsTypeface = ResourcesCompat.getFont(context, R.font.icons)!!

        data = mutableMapOf("info_header_1" to R.string.windFlowNumber,
            "info_header_2" to R.string.perceptionNumber,
            "info_header_3" to R.string.humidityNumber,
            "info_icon_1" to R.string.windFlowIcon,
            "info_icon_2" to R.string.perceptionIcon,
            "info_icon_3" to R.string.humidityIcon,
            "info_text1_1" to R.string.windFlowText,
            "info_text1_2" to R.string.perceptionText,
            "info_text1_3" to R.string.humidityText,
            "week_header_1" to R.string.tomorrow,
            "week_header_2" to R.string.sunday,
            "week_header_3" to R.string.monday,
            "week_header_4" to R.string.tuesday,
            "week_header_5" to R.string.wednesday,
            "week_icon_1" to R.string.tomorrowIcon,
            "week_icon_2" to R.string.sundayIcon,
            "week_icon_3" to R.string.mondayIcon,
            "week_icon_4" to R.string.tuesdayIcon,
            "week_icon_5" to R.string.wednesdayIcon,
            "week_text1_1" to R.string.tomorrowTemperature,
            "week_text1_2" to R.string.sundayTemperature,
            "week_text1_3" to R.string.mondayTemperature,
            "week_text1_4" to R.string.tuesdayTemperature,
            "week_text1_5" to R.string.wednesdayTemperature,
            "week_text2_1" to R.string.tomorrowText,
            "week_text2_2" to R.string.sundayText,
            "week_text2_3" to R.string.mondayText,
            "week_text2_4" to R.string.tuesdayText,
            "week_text2_5" to R.string.wednesdayText,
            "menu_icon_1" to R.string.region,
            "menu_icon_2" to R.string.home,
            "menu_icon_3" to R.string.settings,
            "menu_icon_4" to R.string.link
            )

    }

    fun setMode(itemMode: ItemMode = ItemMode.HEADER, id: Int) {
        val end = id.toString()
        when (itemMode) {
            ItemMode.HEADER -> {
                binding.header.visibility = GONE
                binding.icon.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100f)
                binding.text1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
                binding.text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                binding.text3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
            }
            ItemMode.INFO -> {
                binding.header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
                binding.header.setText(data["info_header_$end"]!!)
                binding.icon.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48f)
                binding.icon.setText(data["info_icon_$end"]!!)
                binding.text1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
                binding.text1.setText(data["info_text1_$end"]!!)
                binding.text2.visibility = GONE
                binding.text3.visibility = GONE
            }
            ItemMode.WEEK -> {
                binding.header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                binding.header.setText(data["week_header_$end"]!!)
                binding.icon.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
                binding.icon.setText(data["week_icon_$end"]!!)
                binding.text1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                binding.text1.setText(data["week_text1_$end"]!!)
                binding.text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                binding.text2.setText(data["week_text2_$end"]!!)
                binding.text3.visibility = GONE
            }
            ItemMode.MENU -> {
                binding.header.visibility = GONE
                binding.icon.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
                binding.icon.setText(data["menu_icon_$end"]!!)
                binding.icon.typeface = iconsTypeface
                binding.text1.visibility = GONE
                binding.text2.visibility = GONE
                binding.text3.visibility = GONE
            }

        }
    }

    enum class ItemMode{
        HEADER,
        INFO,
        WEEK,
        MENU
    }
}
