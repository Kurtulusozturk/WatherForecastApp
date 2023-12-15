package com.example.weatherforecast.utils

import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyAPIList
import com.example.weatherforecast.model.dailymodel.DailyWeatherModel
import com.example.weatherforecast.model.hourlymodel.HourlyWeatherModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor
class MyHelper {

    private fun kelvinToCelcius(kelvin: Double?): Int {
        val celciusDouble = kelvin?.minus(273.15)
        val decimalPart = celciusDouble?.minus(celciusDouble.toInt())
        if (decimalPart!! > 0.5) {
            ceil(celciusDouble)
        } else {
            floor(celciusDouble)
        }
        return celciusDouble.toInt()
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

                        //println("Tarih " + it.dtTxt + "nem " + it.main?.humidity)
                    }
                    val humiditySum = group.fold(0) { acc, weatherData -> acc + weatherData.main?.humidity!! }
                    val humidityAverage = (humiditySum.toDouble() / group.size).toInt()
                    val mostCommonWeather = weatherCountMap.maxByOrNull { it.value }?.key

                    dailyWeatherList.add(DailyWeatherModel(kelvinToCelcius(maxTemp),kelvinToCelcius(minTemp),humidityAverage,mostCommonWeather,date))
                }
            }
            return dailyWeatherList
        }
        else{
            groupedByDate.forEach { (date, group) ->
                if (date == getCurrentDateInFormat("yyyy-MM-dd")){
                    group.forEach {
                        hourlyWeatherList.add(HourlyWeatherModel(kelvinToCelcius(it.main?.temp),it.weather[0].main,it.dtTxt))
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
}