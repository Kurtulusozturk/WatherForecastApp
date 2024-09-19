package com.example.weatherforecast.service.currentweatherservice

import com.example.weatherforecast.model.currentweathermodel.CurrentWeatherModel
import com.example.weatherforecast.utils.RetrofitBaseClassWeatherService
import io.reactivex.Single

class CurrentWeatherService {
    private val BASE_URL = "https://api.openweathermap.org/"

    fun getData(lat : Double, lon : Double) : Single<CurrentWeatherModel> {
        val api = RetrofitBaseClassWeatherService().getService<CurrentWeatherAPI>(BASE_URL)
        return api.getCurrentWeather(lat , lon)
    }
}