package com.example.weatherforecast.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherforecast.model.CitiesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CitySharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("CityPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveSelectedCity(newCity: CitiesModel) {
        // List of selected cities
        val existingCityList = getSelectedCities().toMutableList()

        if (!existingCityList.contains(newCity)){
            // New city add list
            existingCityList.add(newCity)
        }else{
            println("zaten var")
        }


        // Şehir listesini JSON formatına çevirerek SharedPreferences'a kaydet
        val json = gson.toJson(existingCityList)
        sharedPreferences.edit().putString("selectedCities", json).apply()
    }

    fun getSelectedCities(): ArrayList<CitiesModel> {
        val json = sharedPreferences.getString("selectedCities", null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<ArrayList<CitiesModel>>() {}.type)
        } else {
            arrayListOf(CitiesModel("İstanbul","41.0053","28.9770"))
        }
    }

    fun removeSelectedCity(cityName: String) {
        // List of selected cities
        val existingCityList = getSelectedCities().toMutableList()

        // Delete selected city
        existingCityList.removeAll { it.name == cityName }

        val json = gson.toJson(existingCityList)
        sharedPreferences.edit().putString("selectedCities", json).apply()
    }

    fun saveLastSelectedCity(city: CitiesModel) {
        val gson = Gson()
        val json = gson.toJson(city)
        sharedPreferences.edit().putString("lastSelectedCity", json).apply()
    }

    fun getLastSelectedCity(): CitiesModel {
        val gson = Gson()
        val json = sharedPreferences.getString("lastSelectedCity", null)
        return gson.fromJson(json, CitiesModel::class.java)
    }
}