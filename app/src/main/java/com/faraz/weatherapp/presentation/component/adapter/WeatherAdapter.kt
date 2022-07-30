package com.faraz.weatherapp.presentation.component.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faraz.weatherapp.R
import com.faraz.weatherapp.data.source.remote.model.forecast.Forecastday
import com.faraz.weatherapp.domain.model.DArticles
import com.faraz.weatherapp.util.ext.*
import kotlinx.android.synthetic.main.item_weather.view.*


class WeatherAdapter(val weatherList: MutableList<Forecastday>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var listener: AdapterListener? = null
    val animate = Animate()
    private var on_attach = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_weather, false))
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.run {
            onBind(position)
            animate.setAnimation(on_attach, itemView, position)
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {

            val weather = weatherList[position]

            itemView.ivCondition.loadImageCenterCrop("https:" + weather.day.condition.icon)
            itemView.tvCondition.text = weather.day.condition.text
            itemView.tvTemprature.text = weather.day.maxtemp_c.toString()
            itemView.tvDate.text = parseDate(weather.date)
        }
    }

    fun clearDate() {
        weatherList.clear()
        notifyDataSetChanged()
    }

    fun addNews(list: MutableList<Forecastday>) {
        weatherList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                on_attach = false
            }
        })
    }

    interface AdapterListener {
        fun onNewsSelected(weather: DArticles)
    }
}