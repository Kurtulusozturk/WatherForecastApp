package com.example.watherforecast.model.hourlymodel

import com.google.gson.annotations.SerializedName


data class HourlyWeatherModel (

  @SerializedName("time"                 ) var time             : ArrayList<String> = arrayListOf(),
  @SerializedName("temperature_2m"       ) var temperature      : ArrayList<Double> = arrayListOf(),
  @SerializedName("weather_code"         ) var weatherCode      : ArrayList<Int>    = arrayListOf()
)