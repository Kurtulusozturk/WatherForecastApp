package com.example.weatherforecast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.model.CitiesModel
import com.example.weatherforecast.service.citiesservice.CitiesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ChooseLocationViewModel : ViewModel() {

    private val citiesService = CitiesService()
    private val disposable = CompositeDisposable()

    val cities = MutableLiveData<List<CitiesModel>>()
    val citiesError = MutableLiveData<Boolean>()


    fun getCities(){
        disposable.add(
            citiesService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CitiesModel>>(){
                    override fun onSuccess(t: List<CitiesModel>) {
                        cities.value = t
                        citiesError.value = false
                    }

                    override fun onError(e: Throwable) {
                        citiesError.value = true
                    }
                })
        )
    }
}