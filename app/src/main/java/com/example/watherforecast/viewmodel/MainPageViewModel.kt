package com.example.watherforecast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watherforecast.model.currentweathermodel.CurrentWeatherModel
import com.example.watherforecast.model.dailymodel.DailyResponseModel
import com.example.watherforecast.model.dailymodel.DailyWeatherModel
import com.example.watherforecast.model.hourlymodel.HourlyResponseModel
import com.example.watherforecast.model.hourlymodel.HourlyWeatherModel
import com.example.watherforecast.service.currentweatherservice.CurrentWeatherService
import com.example.watherforecast.service.dailyweatherservice.DailyWeatherService
import com.example.watherforecast.service.hourlyweatherservice.HourlyWeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPageViewModel : ViewModel() {
    private val currentWeatherService = CurrentWeatherService()
    private val dailyWeatherService = DailyWeatherService()
    private val hourlyWeatherService = HourlyWeatherService()
    private val disposable = CompositeDisposable()

    val currentWeather = MutableLiveData<CurrentWeatherModel?>()
    val currentWeatherError = MutableLiveData<Boolean>()

    val dailyWeather = MutableLiveData<ArrayList<DailyWeatherModel>?>()
    val dailyWeatherError = MutableLiveData<Boolean>()

    val hourlyWeather = MutableLiveData<HourlyWeatherModel?>()
    val hourlyWeatherError = MutableLiveData<Boolean>()

    fun getCurrentWeather(latitude : Double, longitude : Double){
        var lat = latitude
        var lon = longitude
        if (lat.equals(0.0) || lon.equals(0.0)){
            lat = 41.00
            lon = 28.97
        }
        disposable.add(
            currentWeatherService.getData(lat , lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CurrentWeatherModel>(){
                    override fun onSuccess(t: CurrentWeatherModel) {
                        currentWeather.value = t
                        currentWeatherError.value = false
                    }

                    override fun onError(e: Throwable) {
                        println(e)
                        currentWeatherError.value = true
                    }

                })
        )
    }

    fun getDailyWeather(latitude : Double, longitude : Double){
        var lat = latitude
        var lon = longitude
        if (lat.equals(0.0) || lon.equals(0.0)){
            lat = 41.00
            lon = 27.12
        }
        disposable.add(
        dailyWeatherService.getData(lat , lon)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DailyResponseModel>(){
                override fun onSuccess(t: DailyResponseModel) {
                    dailyWeather.value = t.dailyWeaterModel
                    dailyWeatherError.value = false
                }

                override fun onError(e: Throwable) {
                    println(e)
                    dailyWeatherError.value = true
                }

            })
        )
    }
    fun getHourlyWeather(latitude : Double, longitude : Double){
        var lat = latitude
        var lon = longitude
        if (lat.equals(0.0) || lon.equals(0.0)){
            lat = 41.00
            lon = 27.12
        }
        disposable.add(
            hourlyWeatherService.getData(lat , lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableSingleObserver<HourlyResponseModel>(){
                    override fun onSuccess(t: HourlyResponseModel) {
                        hourlyWeather.value = t.hourlyWeatherModel
                        hourlyWeatherError.value = false
                    }

                    override fun onError(e: Throwable) {
                        dailyWeatherError.value = true
                    }

                })
        )
    }
}