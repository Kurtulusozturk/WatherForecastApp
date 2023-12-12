package com.example.watherforecast.service.currentweatherservice

import com.example.watherforecast.model.currentweathermodel.CurrentWeatherModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CurrentWeatherService {
    //https://api.openweathermap.org/
    //BASE_URL -> https://api.open-meteo.com/
    //EXT -> data/2.5/weather?lat=41.0053&lon=28.9770&appid=8ec9ce3c86c008095b00266b8779432c

    private val BASE_URL = "https://api.openweathermap.org/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CurrentWeatherAPI::class.java)

    fun getData(lat : Double, lon : Double) : Single<CurrentWeatherModel> {
        return api.getCurrentWeather(lat , lon)
    }
}