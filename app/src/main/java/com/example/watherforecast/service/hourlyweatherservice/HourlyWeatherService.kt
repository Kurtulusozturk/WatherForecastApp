package com.example.watherforecast.service.hourlyweatherservice

import com.example.watherforecast.model.hourlymodel.HourlyResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HourlyWeatherService {

    private val BASE_URL = "https://api.open-meteo.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(HourlyWeatherAPI::class.java)

    fun getData(lat: Double, lon: Double) : Single<HourlyResponseModel> {
        return api.getHourlyWeather(lat, lon)
    }

}