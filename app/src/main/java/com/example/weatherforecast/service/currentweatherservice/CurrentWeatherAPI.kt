package com.example.weatherforecast.service.currentweatherservice

import com.example.weatherforecast.model.currentweathermodel.CurrentWeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherAPI {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") fields: String = "YourApiKey"
    ) : Single<CurrentWeatherModel>
}