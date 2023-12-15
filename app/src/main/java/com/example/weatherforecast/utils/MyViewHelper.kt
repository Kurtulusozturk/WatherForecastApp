package com.example.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor

object MyViewHelper {


    fun kelvinToCelcius(kelvin: Double): String {
        val celciusDouble = kelvin.minus(273.15)
        val decimalPart = celciusDouble.minus(celciusDouble.toInt())
        if (decimalPart > 0.5) {
            ceil(celciusDouble)
        } else {
            floor(celciusDouble)
        }
        return celciusDouble.toInt().toString() + " °C"
    }
    fun getCurrentDateInFormat(format: String): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
    fun setImageBackground(){

    }

}