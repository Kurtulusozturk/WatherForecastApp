package com.example.weatherforecast.service.dailyweatherservice

import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyWeatherAPI {
    @GET("data/2.5/forecast")
    fun getDailyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") fields: String = "YourApiKey"
    ) : Single<DailyAndHourlyResponseModel>
}