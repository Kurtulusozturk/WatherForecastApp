package com.example.weatherforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.DailyWeatherRecyclerViewBinding
import com.example.weatherforecast.model.dailymodel.DailyWeatherModel

class DailyRecyclerViewAdapter(val dailyList : ArrayList<DailyWeatherModel>)
    :RecyclerView.Adapter<DailyRecyclerViewAdapter.DailyViewHolder>(){

    class DailyViewHolder(val view : DailyWeatherRecyclerViewBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DailyWeatherRecyclerViewBinding>(inflater,R.layout.daily_weather_recycler_view,parent,false)
        return DailyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.view.dailyWeather = dailyList[position]
    }

    fun updateData(newDailyList : ArrayList<DailyWeatherModel>){
        dailyList.clear()
        dailyList.addAll(newDailyList)
        notifyDataSetChanged()
    }
}