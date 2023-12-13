package com.example.weatherforecast.model

import com.google.gson.annotations.SerializedName


data class CitiesModel (
  @SerializedName("id"         ) var id         : Int?    = null,
  @SerializedName("name"       ) var name       : String? = null,
  @SerializedName("latitude"   ) var latitude   : String? = null,
  @SerializedName("longitude"  ) var longitude  : String? = null,
  @SerializedName("population" ) var population : Int?    = null,
  @SerializedName("region"     ) var region     : String? = null

)