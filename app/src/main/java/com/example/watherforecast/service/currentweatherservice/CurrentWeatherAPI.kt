package com.example.watherforecast.service.currentweatherservice

import com.example.watherforecast.model.currentweathermodel.CurrentWeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherAPI {
    //BASE_URL -> https://api.openweathermap.org/
    //EXT -> data/2.5/weather?lat=52.00&lon=65.21&appid=8ec9ce3c86c008095b00266b8779432c

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") fields: String = "8ec9ce3c86c008095b00266b8779432c"
    ) : Single<CurrentWeatherModel>
}