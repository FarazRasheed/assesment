package com.ewind.newsapi.presentation.main.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ewind.newsapi.R
import com.ewind.newsapi.domain.model.DUser
import com.ewind.newsapi.presentation.main.base.BaseFragment
import com.ewind.newsapi.util.Resource
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    private val profileViewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel.userLiveData.observe(this, Observer { setData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.getUser()

        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_dropdown_item_1line,
            ProfileViewModel.CITIES
        )

        etCity.setAdapter(adapter)
        btn_done.setOnClickListener {
            if (!etCity.text.isNullOrEmpty()){

                val action = ProfileFragmentDirections.actionFragmentLocationToFragmentForecast(etCity.text.toString())
                findNavController().navigate(action)
            }else{
                Toast.makeText(context, "Please select city", Toast.LENGTH_LONG).show()
            }
//            Navigation.findNavController(it)
//                .navigate(R.id.action_fragment_location_to_fragment_forecast)
//            startActivityForResult<ProfileEditActivity>(REQ_USER_UPDATE) {}
        }
    }

    private fun setData(resource: Resource<DUser>?) {
        resource?.let {
            when (it.state) {
//                ResourceState.LOADING -> {
//                    pull_refresh.startRefresh()
//                }
//                ResourceState.SUCCESS -> {
//                    pull_refresh.stopRefresh()
//                    tv_user_name.text = it.data?.name
//                }
//                ResourceState.ERROR -> {
//                    pull_refresh.stopRefresh()
//                    //Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
//                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_USER_UPDATE) {
            if (resultCode == Activity.RESULT_OK) {
                profileViewModel.getUser()
            }
        }
    }


}