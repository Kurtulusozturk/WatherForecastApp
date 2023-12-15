package com.example.weatherforecast.service.citiesservice

import com.example.weatherforecast.model.CitiesModel
import com.example.weatherforecast.service.dailyweatherservice.DailyWeatherAPI
import com.example.weatherforecast.utils.RetrofitBaseClassWeatherService
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CitiesService {
    //BASE_URL -> https://gist.githubusercontent.com/
    //EXT -> ozdemirburak/4821a26db048cc0972c1beee48a408de/raw/4754e5f9d09dade2e6c461d7e960e13ef38eaa88/cities_of_turkey.json

    private val BASE_URL = "https://gist.githubusercontent.com/"
    fun getData() : Single<List<CitiesModel>> {
        val api = RetrofitBaseClassWeatherService().retrofit<CitiesAPI>(BASE_URL)
        return api.getCities()
    }
}