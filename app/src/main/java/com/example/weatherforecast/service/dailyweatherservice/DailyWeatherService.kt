package com.example.weatherforecast.service.dailyweatherservice

import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DailyWeatherService {
    private val BASE_URL = "https://api.openweathermap.org/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DailyWeatherAPI::class.java)

    fun getData(lat : Double , lon : Double) : Single<DailyAndHourlyResponseModel> {
        return api.getDailyWeather(lat , lon)
    }

}