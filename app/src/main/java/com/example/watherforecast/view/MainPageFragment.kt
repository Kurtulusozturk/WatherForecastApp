package com.example.watherforecast.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watherforecast.databinding.FragmentMainPageBinding
import com.example.watherforecast.utils.MyHelper
import com.example.watherforecast.viewmodel.MainPageViewModel

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private var mainPageViewModel: MainPageViewModel = MainPageViewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mainPageViewModel.getCurrentWeather(0.0,0.0)
        mainPageViewModel.getDailyWeather(0.0,0.0)
        mainPageViewModel.getHourlyWeather(0.0,0.0)
        observeLiveData()
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeLiveData(){
        mainPageViewModel.currentWeather.observe(viewLifecycleOwner) { currentWeather ->
            currentWeather?.let {
                println(it)
            }
        }
        mainPageViewModel.dailyWeather.observe(viewLifecycleOwner) { dailyWeather ->
            dailyWeather?.let {
                println(it)
                MyHelper().calculateDayDatasForWeek(it)
            }
        }
        mainPageViewModel.hourlyWeather.observe(viewLifecycleOwner){hourlyWeather ->
            hourlyWeather?.let {
                println(it)
            }
        }
    }

}