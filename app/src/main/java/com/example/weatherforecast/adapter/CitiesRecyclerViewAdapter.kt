package com.example.weatherforecast.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.CitiesRecyclerViewBinding
import com.example.weatherforecast.model.CitiesModel
import com.example.weatherforecast.utils.CitySharedPreferences
import com.example.weatherforecast.view.ChooseLocationFragmentDirections


class CitiesRecyclerViewAdapter(private var citiesList : ArrayList<CitiesModel>, private val fragmentView: View)
    : RecyclerView.Adapter<CitiesRecyclerViewAdapter.CitiesViewHolder>() {
    private lateinit var context : Context

        class CitiesViewHolder(val view : CitiesRecyclerViewBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CitiesRecyclerViewBinding>(inflater,
            R.layout.cities_recycler_view,parent,false)
        return CitiesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.view.citiesList = citiesList[position]
        println(citiesList[position])
        holder.view.delete.setOnClickListener {
            val citySharedPreferences = CitySharedPreferences(context)
            citySharedPreferences.removeSelectedCity(citiesList[position].name.toString())
            updateData(citySharedPreferences.getSelectedCities())
        }
        holder.view.onCityItemClicked.setOnClickListener {
            val navController = fragmentView.findNavController()
            val action = ChooseLocationFragmentDirections.actionChooseLocationFragmentToMainPageFragment2(citiesList[position])
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.mainPageFragment, true)
                .build()
            navController.navigate(action,navOptions)
        }
    }

    fun updateData(newcCitiesList : ArrayList<CitiesModel>){
        citiesList.clear()
        citiesList.addAll(newcCitiesList)
        println(citiesList)
        notifyDataSetChanged()
    }
}