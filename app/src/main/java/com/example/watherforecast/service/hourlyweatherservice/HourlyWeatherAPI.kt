package com.example.watherforecast.service.hourlyweatherservice

import com.example.watherforecast.model.hourlymodel.HourlyResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HourlyWeatherAPI {
    //https://api.open-meteo.com/
    //BASE_URL -> https://api.open-meteo.com/
    //EXT -> v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,weather_code&timezone=Europe%2FMoscow&forecast_days=1

    @GET("v1/forecast")
    fun getHourlyWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("hourly") fields: String = "temperature_2m,weather_code",
        @Query("timezone") timezone: String = "Europe/Moscow"
        ) : Single<HourlyResponseModel>
}