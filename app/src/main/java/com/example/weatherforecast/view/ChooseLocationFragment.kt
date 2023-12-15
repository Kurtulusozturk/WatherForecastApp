package com.example.weatherforecast.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.adapter.CitiesRecyclerViewAdapter
import com.example.weatherforecast.databinding.FragmentChooseLocationBinding
import com.example.weatherforecast.model.CitiesModel
import com.example.weatherforecast.utils.CitySharedPreferences
import com.example.weatherforecast.viewmodel.ChooseLocationViewModel


class ChooseLocationFragment : Fragment() {
    private lateinit var binding: FragmentChooseLocationBinding
    private var chooseLocationViewModel: ChooseLocationViewModel = ChooseLocationViewModel()
    private lateinit var citySharedPreferences : CitySharedPreferences
    private lateinit var citiesAdapter : CitiesRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        citySharedPreferences = CitySharedPreferences(requireContext())
        binding = FragmentChooseLocationBinding.inflate(inflater, container, false)
        binding.viewModel = chooseLocationViewModel
        binding.lifecycleOwner = this
        binding.citiesRecyclerView.layoutManager = LinearLayoutManager(context)
        citiesAdapter = CitiesRecyclerViewAdapter(arrayListOf(),binding.root)
        binding.citiesRecyclerView.adapter = citiesAdapter
        citiesAdapter.updateData(citySharedPreferences.getSelectedCities())
        chooseLocationViewModel.getCities()
        observeLiveData()
        return binding.root
    }
    private fun observeLiveData(){
        chooseLocationViewModel.cities.observe(viewLifecycleOwner){ cities ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities.map { it.name })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.citySpinner.adapter = adapter
            setSpinner(cities)
            }
    }

    private fun setSpinner(citiesModels: List<CitiesModel>) {
        var cityName: String? = null
        var cityLatitude: String? = null
        var cityLongitude: String? = null
        binding.citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = citiesModels[position]
                cityName = selectedCity.name
                cityLatitude = selectedCity.latitude
                cityLongitude = selectedCity.longitude
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.add.setOnClickListener {
            val newCity = CitiesModel(cityName, cityLatitude, cityLongitude)
            citySharedPreferences.saveSelectedCity(newCity)
            citiesAdapter.updateData(citySharedPreferences.getSelectedCities())
        }
    }
}
