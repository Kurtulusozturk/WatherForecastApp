package com.example.weatherforecast.model

import com.google.gson.annotations.SerializedName


data class CitiesModel (
  @SerializedName("name"       ) var name       : String? = null,
  @SerializedName("latitude"   ) var latitude   : String? = null,
  @SerializedName("longitude"  ) var longitude  : String? = null
)