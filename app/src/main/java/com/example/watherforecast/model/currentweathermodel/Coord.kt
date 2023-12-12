package com.example.watherforecast.model.currentweathermodel

import com.google.gson.annotations.SerializedName


data class Coord (

  @SerializedName("lon" ) var lon : Double? = null,
  @SerializedName("lat" ) var lat : Int?    = null

)