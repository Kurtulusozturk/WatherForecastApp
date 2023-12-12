package com.example.watherforecast.model.hourlymodel

import com.google.gson.annotations.SerializedName


data class HourlyUnitsModel (

  @SerializedName("time"                 ) var time             : String? = null,
  @SerializedName("temperature_2m"       ) var temperature      : String? = null,
  @SerializedName("relative_humidity_2m" ) var relativeHumidity : String? = null,
  @SerializedName("weather_code"         ) var weatherCode      : String? = null

)