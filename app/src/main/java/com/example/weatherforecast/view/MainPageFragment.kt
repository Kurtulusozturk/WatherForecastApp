package com.example.weatherforecast.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.viewmodel.MainPageViewModel

class MainPageFragment : Fragment() {
    private var mainPageViewModel: MainPageViewModel = MainPageViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mainPageViewModel.getCurrentWeather(0.0,0.0)
        mainPageViewModel.getDailyAndHourlyData(0.0,0.0)
        observeLiveData()
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    private fun observeLiveData(){
        mainPageViewModel.currentWeather.observe(viewLifecycleOwner) { currentWeather ->
            currentWeather?.let {
                println(it)
            }
        }
        mainPageViewModel.dailyAndHourlyAPIList.observe(viewLifecycleOwner){dailyAndHourlyAPIList ->
            dailyAndHourlyAPIList?.let {
                mainPageViewModel.getDailyWeather()
                mainPageViewModel.getHourlyWeather()
            }
        }
        mainPageViewModel.dailyWeather.observe(viewLifecycleOwner) { dailyWeather ->
            dailyWeather?.let {
                it.forEach{
                    println(it)
                }
                //println(it)
            }
        }
        mainPageViewModel.hourlyWeather.observe(viewLifecycleOwner){ hourlyWeather ->
            hourlyWeather?.let {
                it.forEach{
                    println(it)
                }
            }
        }
        /*
                mainPageViewModel.dailyAndHourlyAPIList.observe(viewLifecycleOwner){ dailyAndHourlyAPIList ->
            dailyAndHourlyAPIList.let {
                MyHelper().deneme(dailyAndHourlyAPIList,null)
            }
        }
         */

        /*
                mainPageViewModel.hourlyWeather.observe(viewLifecycleOwner){hourlyWeather ->
            hourlyWeather?.let {
                println(it)
            }
        }
         */
    }

}