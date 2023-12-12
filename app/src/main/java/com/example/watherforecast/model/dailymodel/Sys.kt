package com.example.watherforecast.model.dailymodel

import com.google.gson.annotations.SerializedName


data class Sys (

  @SerializedName("pod" ) var pod : String? = null

)