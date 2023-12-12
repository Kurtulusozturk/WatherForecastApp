package com.example.watherforecast.service.dailyweatherservice

import com.example.watherforecast.model.dailymodel.DailyResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyWeatherAPI {
    //BASE_URL -> https://api.openweathermap.org/
    //EXT -> data/2.5/forecast?lat=41.0053&lon=28.9770&appid=8ec9ce3c86c008095b00266b8779432c

    @GET("data/2.5/forecast")
    fun getDailyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") fields: String = "8ec9ce3c86c008095b00266b8779432c"
    ) : Single<DailyResponseModel>
}