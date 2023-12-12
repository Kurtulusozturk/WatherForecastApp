package com.example.watherforecast.model.dailymodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DailyResponseModel (

  @SerializedName("cod"     ) var cod     : String?         = null,
  @SerializedName("message" ) var message : Int?            = null,
  @SerializedName("cnt"     ) var cnt     : Int?            = null,
  @SerializedName("list"    ) var dailyWeaterModel  : ArrayList<DailyWeatherModel>? = arrayListOf(),
  @SerializedName("city"    ) var city    : City?           = City()

) : Serializable