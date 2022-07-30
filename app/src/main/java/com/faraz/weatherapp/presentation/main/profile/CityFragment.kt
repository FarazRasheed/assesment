package com.faraz.weatherapp.presentation.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.faraz.weatherapp.R
import com.faraz.weatherapp.presentation.main.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_city.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : BaseFragment() {

    private val cityViewModel by viewModel<CityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_city, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_dropdown_item_1line,
            CityViewModel.CITIES
        )

        etCity.setAdapter(adapter)
        btn_done.setOnClickListener {
            if (!etCity.text.isNullOrEmpty()){

                val action = CityFragmentDirections.actionFragmentLocationToFragmentForecast(etCity.text.toString())
                findNavController().navigate(action)
            }else{
                Toast.makeText(context, "Please select city", Toast.LENGTH_LONG).show()
            }
        }
    }
}