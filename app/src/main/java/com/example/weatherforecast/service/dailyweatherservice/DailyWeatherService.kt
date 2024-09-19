package com.example.weatherforecast.service.dailyweatherservice

import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyResponseModel
import com.example.weatherforecast.utils.RetrofitBaseClassWeatherService
import io.reactivex.Single

class DailyWeatherService {
    private val BASE_URL = "https://api.openweathermap.org/"

    fun getData(lat : Double , lon : Double) : Single<DailyAndHourlyResponseModel> {
        val api = RetrofitBaseClassWeatherService().getService<DailyWeatherAPI>(BASE_URL)
        return api.getDailyWeather(lat , lon)
    }

}