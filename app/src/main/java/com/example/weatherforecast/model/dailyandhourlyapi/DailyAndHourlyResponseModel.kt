package com.example.weatherforecast.model.dailyandhourlyapi

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DailyAndHourlyResponseModel (

  @SerializedName("cod"     ) var cod     : String?         = null,
  @SerializedName("message" ) var message : Int?            = null,
  @SerializedName("cnt"     ) var cnt     : Int?            = null,
  @SerializedName("list"    ) var dailyAndHourlyAPIList  : ArrayList<DailyAndHourlyAPIList>? = arrayListOf(),
  @SerializedName("city"    ) var city    : City?           = City()

) : Serializable