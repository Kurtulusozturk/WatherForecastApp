package com.example.weatherforecast.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.adapter.DailyRecyclerViewAdapter
import com.example.weatherforecast.adapter.HourlyRecyclerViewAdapter
import com.example.weatherforecast.databinding.FragmentMainPageBinding
import com.example.weatherforecast.model.CitiesModel
import com.example.weatherforecast.utils.CitySharedPreferences
import com.example.weatherforecast.viewmodel.MainPageViewModel

class MainPageFragment : Fragment() {
    private var mainPageViewModel: MainPageViewModel = MainPageViewModel()
    private val dailyAdapter = DailyRecyclerViewAdapter(arrayListOf())
    private val hourlyAdapter = HourlyRecyclerViewAdapter(arrayListOf())
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var selectedCity : CitiesModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        observeLiveData()

        try{
            //Remember Value
            arguments?.let {
                try {
                    selectedCity = MainPageFragmentArgs.fromBundle(it).selectedCity
                    CitySharedPreferences(requireContext()).saveLastSelectedCity(selectedCity)
                }catch (e:Exception){
                    selectedCity = CitySharedPreferences(requireContext()).getLastSelectedCity()
                }
            }
            mainPageViewModel.getCurrentWeather(selectedCity.latitude!!.toDouble(),selectedCity.longitude!!.toDouble())
            mainPageViewModel.getDailyAndHourlyData(selectedCity.latitude!!.toDouble(),selectedCity.longitude!!.toDouble())

        }catch (e : Exception){
            //Default Value
            selectedCity = CitiesModel("İstanbul","0.0","0.0")
            mainPageViewModel.getCurrentWeather(0.0,0.0)
            mainPageViewModel.getDailyAndHourlyData(0.0,0.0)
        }

        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        binding.viewModel = mainPageViewModel
        binding.lifecycleOwner = this
        binding.cityModel = selectedCity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dailyWeatherRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.dailyWeatherRecyclerView.adapter = dailyAdapter
        binding.hourlyWeatherRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.hourlyWeatherRecyclerView.adapter = hourlyAdapter
        binding.goToSelectCities.setOnClickListener {
            val action = MainPageFragmentDirections.actionMainPageFragmentToChooseLocationFragment3()
            findNavController().navigate(action)
        }
    }
    private fun observeLiveData(){
        mainPageViewModel.dailyAndHourlyAPIList.observe(viewLifecycleOwner){dailyAndHourlyAPIList ->
            dailyAndHourlyAPIList?.let {
                mainPageViewModel.getDailyWeather()
                mainPageViewModel.getHourlyWeather()
            }
        }
        mainPageViewModel.dailyWeather.observe(viewLifecycleOwner) { dailyWeather ->
            dailyWeather?.let {
                dailyAdapter.updateData(it)

            }
        }
        mainPageViewModel.hourlyWeather.observe(viewLifecycleOwner){ hourlyWeather ->
            hourlyWeather?.let {
                hourlyAdapter.updateData(it)
            }
        }
    }

}