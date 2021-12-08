package ru.daniil.firstapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarDto(
    val number: Int,
    val type: String,
    val owner: String,
) : Parcelable