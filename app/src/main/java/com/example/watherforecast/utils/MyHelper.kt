package com.example.watherforecast.utils

import com.example.watherforecast.model.dailymodel.DailyWeatherModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor
class MyHelper {
    fun kelvinToCelcius(kelvin: Double?): Int {
        val celciusDouble = kelvin?.minus(273.15)
        val decimalPart = celciusDouble?.minus(celciusDouble.toInt())
        if (decimalPart!! > 0.5) {
            ceil(celciusDouble)
        } else {
            floor(celciusDouble)
        }
        return celciusDouble.toInt()
    }

    fun calculateDayDatasForWeek(dailyWeaterModel: ArrayList<DailyWeatherModel>){
        val fiveDay = "null"
        var i = 0
        val dayIndex = arrayListOf<Int>()
        dayIndex.clear()

        val groupedByDate = dailyWeaterModel.groupBy {
            it.dtTxt?.substring(0, 10)
            //it.dtTxt?.dateform()
        }
        groupedByDate.forEach { (date, group) ->
            if (date != getCurrentDateInFormat("yyyy-MM-dd")){
                //Burası günlük verilerin ayarlanacağı sınıf
                val (maxTemp, minTemp) = group.map { it.main!!.temp }.let {
                    if (it.isEmpty()) null to null
                    else it.maxByOrNull { it!! } to it.minByOrNull { it!! }
                }
                println("Tarih $date: $group")
                // date, maxTemp, minTemp, birtek hava durumu kaldı sonra bunu modelle viewde kullan
            }else{
                //Burası saatlik verilerin düzenleneceği grup
                //burda dönen groupu direkta alıp kullanabilirsin
                println("Tarih $date: $group")
            }
        }
    }

    fun getCurrentDateInFormat(format: String): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}