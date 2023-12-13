package com.example.weatherforecast.model.currentweathermodel

import com.google.gson.annotations.SerializedName

data class Rain (
    @SerializedName("1h" ) var oneh : Double? = null
)
