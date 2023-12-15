package com.example.weatherforecast.utils

import android.widget.ImageView
import com.example.weatherforecast.R
import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyAPIList
import com.example.weatherforecast.model.dailymodel.DailyWeatherModel
import com.example.weatherforecast.model.hourlymodel.HourlyWeatherModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor
class MyHelper {

    private fun kelvinToCelsius(kelvin: Double?): Int {
        val celsiusDouble = kelvin?.minus(273.15)
        val decimalPart = celsiusDouble?.minus(celsiusDouble.toInt())
        if (decimalPart!! > 0.5) {
            ceil(celsiusDouble)
        } else {
            floor(celsiusDouble)
        }
        return celsiusDouble.toInt()
    }

    fun getDatasForDailyOrHourly(dailyAndHourlyWeaterModel: ArrayList<DailyAndHourlyAPIList>, dailyOrHourly : String?): Any? {
        val dailyWeatherList = ArrayList<DailyWeatherModel>()
        val hourlyWeatherList = ArrayList<HourlyWeatherModel>()

        dailyWeatherList.clear()
        hourlyWeatherList.clear()

        val groupedByDate = dailyAndHourlyWeaterModel.groupBy {
            it.dtTxt?.substring(0, 10)
        }
        if (dailyOrHourly == "daily"){
            groupedByDate.forEach { (date, group) ->
                if (date != getCurrentDateInFormat("yyyy-MM-dd")){
                    val (maxTemp, minTemp) = group.map { it.main!!.temp }.let { it ->
                        if (it.isEmpty()) null to null
                        else it.maxByOrNull { it!! } to it.minByOrNull { it!! }
                    }

                    val weatherCountMap = mutableMapOf<String, Int>()
                    group.forEach {
                        val weatherMain = it.weather[0].main
                        weatherCountMap[weatherMain!!] = (weatherCountMap[weatherMain] ?: 0) + 1
                    }
                    val mostCommonWeather = weatherCountMap.maxByOrNull { it.value }?.key

                    val humiditySum = group.fold(0) { acc, weatherData -> acc + weatherData.main?.humidity!! }
                    val humidityAverage = (humiditySum.toDouble() / group.size).toInt()

                    dailyWeatherList.add(DailyWeatherModel(kelvinToCelsius(maxTemp),kelvinToCelsius(minTemp),humidityAverage,mostCommonWeather,formatDate(date)))
                }
            }
            return dailyWeatherList
        }
        else{
            groupedByDate.forEach { (date, group) ->
                if (date == getCurrentDateInFormat("yyyy-MM-dd")){
                    group.forEach {
                        hourlyWeatherList.add(HourlyWeatherModel(kelvinToCelsius(it.main?.temp),it.weather[0].main,formatDate(it.dtTxt)))
                    }
                    return hourlyWeatherList
                }
            }
        }
        return null
    }

    private fun getCurrentDateInFormat(format: String): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun setImageBackground(imageView: ImageView, weather : String?){
        val drawableResId = when(weather?.toLowerCase()){
            "clouds" -> R.drawable.cloud
            "clear"  -> R.drawable.clear
            "snow"  -> R.drawable.snowy
            "rain"  -> R.drawable.rain
            else     -> R.drawable.cloud
        }
        imageView.setBackgroundResource(drawableResId)
    }

    fun formatDate(dateString: String?): String {

        if (dateString!!.contains(":")) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val outputFormat2 = SimpleDateFormat("HH:mm")
            val dateObject = inputFormat.parse(dateString) as Date
            return outputFormat2.format(dateObject)
        }else{
            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat1 = SimpleDateFormat("dd/MM")
            val dateObject = inputFormat.parse(dateString) as Date
            return outputFormat1.format(dateObject)
        }

    }
}