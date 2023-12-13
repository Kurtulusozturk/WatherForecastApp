package com.example.weatherforecast.dataModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.weatherforecast.utils.BR

class AllValuesDataModel : BaseObservable() {
    @get:Bindable
    var totalAmount: Double? = null
        set(value) {
            field = value
           // notifyPropertyChanged(BR.totalAmount)
        }
}