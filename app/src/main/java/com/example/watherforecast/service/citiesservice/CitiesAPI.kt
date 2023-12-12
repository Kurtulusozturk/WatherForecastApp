package com.example.watherforecast.service.citiesservice

import com.example.watherforecast.model.CitiesModel
import io.reactivex.Single
import retrofit2.http.GET

interface CitiesAPI {
    //BASE_URL -> https://gist.githubusercontent.com/
    //EXT -> ozdemirburak/4821a26db048cc0972c1beee48a408de/raw/4754e5f9d09dade2e6c461d7e960e13ef38eaa88/cities_of_turkey.json

    @GET("ozdemirburak/4821a26db048cc0972c1beee48a408de/raw/4754e5f9d09dade2e6c461d7e960e13ef38eaa88/cities_of_turkey.json")
    fun getCities() : Single<List<CitiesModel>>
}