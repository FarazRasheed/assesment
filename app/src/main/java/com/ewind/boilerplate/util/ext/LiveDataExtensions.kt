package com.ewind.boilerplate.util.ext

import androidx.lifecycle.MutableLiveData
import com.ewind.boilerplate.util.Resource
import com.ewind.boilerplate.util.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T, message: String?) =
    postValue(Resource(ResourceState.SUCCESS, data, message))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(
        Resource(
            ResourceState.LOADING,
            value?.data
        )
    )

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(
        Resource(
            ResourceState.ERROR,
            value?.data,
            message
        )
    )