package com.example.weatherforecast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.model.currentweathermodel.CurrentWeatherModel
import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyAPIList
import com.example.weatherforecast.model.dailymodel.DailyWeatherModel
import com.example.weatherforecast.model.dailyandhourlyapi.DailyAndHourlyResponseModel
import com.example.weatherforecast.model.hourlymodel.HourlyWeatherModel
import com.example.weatherforecast.service.currentweatherservice.CurrentWeatherService
import com.example.weatherforecast.service.dailyweatherservice.DailyWeatherService
import com.example.weatherforecast.utils.MyHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPageViewModel : ViewModel() {
    private val currentWeatherService = CurrentWeatherService()
    private val dailyAndHourlyWeatherService = DailyWeatherService()
    private val disposable = CompositeDisposable()

    val dailyAndHourlyAPIList = MutableLiveData<ArrayList<DailyAndHourlyAPIList>?>()

    val currentWeather = MutableLiveData<CurrentWeatherModel?>()
    val currentWeatherError = MutableLiveData<Boolean>()

    val dailyWeather = MutableLiveData<ArrayList<DailyWeatherModel>>()
    val dailyWeatherError = MutableLiveData<Boolean>()

    val hourlyWeather = MutableLiveData<ArrayList<HourlyWeatherModel>>()

    fun getCurrentWeather(latitude : Double, longitude : Double){
        var lat = latitude
        var lon = longitude
        if (lat.equals(0.0) || lon.equals(0.0)){
            lat = 41.00
            lon = 28.97
        }
        disposable.add(
            currentWeatherService.getData(lat , lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CurrentWeatherModel>(){
                    override fun onSuccess(t: CurrentWeatherModel) {
                        currentWeather.value = t
                        currentWeatherError.value = false
                    }

                    override fun onError(e: Throwable) {
                        currentWeatherError.value = true
                    }

                })
        )
    }

    fun getDailyAndHourlyData(latitude : Double, longitude : Double){
        var lat = latitude
        var lon = longitude
        if (lat.equals(0.0) || lon.equals(0.0)){
            lat = 41.00
            lon = 27.12
        }
        disposable.add(
            dailyAndHourlyWeatherService.getData(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DailyAndHourlyResponseModel>(){
                    override fun onSuccess(t: DailyAndHourlyResponseModel) {
                        dailyAndHourlyAPIList.value = t.dailyAndHourlyAPIList
                        dailyWeatherError.value = false
                    }

                    override fun onError(e: Throwable) {
                        dailyWeatherError.value = true
                    }

                })
        )
    }

    fun getDailyWeather(){
        dailyAndHourlyAPIList.value?.let {
            dailyWeather.value = MyHelper().getDatasForDailyOrHourly(it, "daily") as ArrayList<DailyWeatherModel>
        }
    }
    fun getHourlyWeather(){
        dailyAndHourlyAPIList.value?.let {
            hourlyWeather.value = MyHelper().getDatasForDailyOrHourly(it, "hourly") as ArrayList<HourlyWeatherModel>
        }
    }
}