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
        // Önceden seçilmiş şehir listesini al
        val existingCityList = getSelectedCities().toMutableList()

        if (!existingCityList.contains(newCity)){
            // Yeni seçilen şehiri listeye ekle
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
            arrayListOf(CitiesModel())
        }
    }

    fun removeSelectedCity(cityName: String) {
        // Önceden seçilmiş şehir listesini al
        val existingCityList = getSelectedCities().toMutableList()

        // Verilen isimdeki şehiri listeden sil
        existingCityList.removeAll { it.name == cityName }

        // Şehir listesini JSON formatına çevirerek SharedPreferences'a kaydet
        val json = gson.toJson(existingCityList)
        sharedPreferences.edit().putString("selectedCities", json).apply()
    }
    fun clearSelectedCities() {
        // SharedPreferences'tan şehir listesini temizle
        sharedPreferences.edit().remove("selectedCities").apply()
    }
}