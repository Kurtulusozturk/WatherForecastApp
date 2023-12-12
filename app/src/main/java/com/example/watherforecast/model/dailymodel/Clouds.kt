package com.example.watherforecast.model.dailymodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Clouds (

  @SerializedName("all" ) var all : Int? = null

) : Serializable