package com.example.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor

object MyViewHelper {


    fun kelvinToCelsius(kelvin: Double): String {
        val celsiusDouble = kelvin.minus(273.15)
        val decimalPart = celsiusDouble.minus(celsiusDouble.toInt())
        if (decimalPart > 0.5) {
            ceil(celsiusDouble)
        } else {
            floor(celsiusDouble)
        }
        return celsiusDouble.toInt().toString() + " °C"
    }
    fun getCurrentDateInFormat(format: String): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}