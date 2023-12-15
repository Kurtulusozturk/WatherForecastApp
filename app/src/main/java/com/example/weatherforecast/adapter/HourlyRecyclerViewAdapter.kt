package com.example.weatherforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.HourlyWeatherRecyclerViewBinding
import com.example.weatherforecast.model.hourlymodel.HourlyWeatherModel


class HourlyRecyclerViewAdapter(val hourlyList : ArrayList<HourlyWeatherModel>):RecyclerView.Adapter<HourlyRecyclerViewAdapter.HourlyViewHolder>() {

    class HourlyViewHolder(val view : HourlyWeatherRecyclerViewBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HourlyWeatherRecyclerViewBinding>(inflater, R.layout.hourly_weather_recycler_view,parent,false)
        return HourlyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.view.hourlyWeather = hourlyList[position]
    }

    fun updateData(newHourlyList : ArrayList<HourlyWeatherModel>){
        hourlyList.clear()
        hourlyList.addAll(newHourlyList)
        notifyDataSetChanged()
    }

}