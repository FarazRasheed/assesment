package com.faraz.weatherapp.presentation.main.topnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.faraz.weatherapp.util.ext.showToast
import com.faraz.weatherapp.R
import com.faraz.weatherapp.data.source.remote.model.forecast.Weather
import com.faraz.weatherapp.presentation.component.adapter.WeatherAdapter
import com.faraz.weatherapp.presentation.main.base.BaseFragment
import com.faraz.weatherapp.util.Msg
import com.faraz.weatherapp.util.PaginationScrollListener
import com.faraz.weatherapp.util.Resource
import com.faraz.weatherapp.util.ResourceState
import com.faraz.weatherapp.util.ext.*
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.android.synthetic.main.today_weather_card.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherForecastFragment : BaseFragment(){

    private val WFViewModel by viewModel<WFViewModel>()
    private lateinit var weatherAdapter: WeatherAdapter
    private val args: WeatherForecastFragmentArgs by navArgs<WeatherForecastFragmentArgs>()
    private lateinit var city: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WFViewModel.weatherliveDate.observe(
            this,
            androidx.lifecycle.Observer { updateWeather(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)
        city = args.city
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherAdapter = WeatherAdapter(mutableListOf())
        rv_top_news.adapter = weatherAdapter
        rv_top_news.addOnScrollListener(object :
            PaginationScrollListener(rv_top_news.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                getTopNews()
            }

            override fun isLastPage(): Boolean {
               return true
            }

            override fun isLoading(): Boolean {
                return WFViewModel.isLoading
            }

        })

        pull_refresh.setOnRefreshListener {
            refreshData()
            getTopNews()
        }

        getTopNews()
    }

    private fun getTopNews() {
        context?.withNetwork(
            {
                WFViewModel.getWeatherForeCast(city)
            }, {
                Msg.INTERNET_ISSUE.showToast(context!!)
            }
        )
    }

    private fun refreshData() {
        weatherAdapter.clearDate()
    }
    private fun updateWeather(resource: Resource<Weather>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    pull_refresh.startRefresh()
                }
                ResourceState.SUCCESS -> {
                    pull_refresh.stopRefresh()
                    it.data?.let { weather ->
                        noDATA.visibility = View.GONE
                        todayCard.visibility = View.VISIBLE
                        weatherAdapter.addNews(weather.forecast.forecastday)
                        tvCity.text = city
                        tvTodayDate.text = parseDate(weather.current.last_updated.substring(0, 10))
                        ivTodayCondition.loadImageCenterCrop("https:" + weather.current.condition.icon)
                        tvTodayTemprature.text = weather.current.temp_c.toString()
                        tvWind.text = weather.current.wind_kph.toString()+"km/h"
                        tvHumi.text = weather.current.humidity.toString()+"%"
                    }
                }
                ResourceState.ERROR -> {
                    noDATA.visibility = View.VISIBLE
                    todayCard.visibility = View.GONE
                    pull_refresh.stopRefresh()
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}