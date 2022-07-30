package com.faraz.weatherapp.util.network

import android.util.Log
import org.json.JSONObject
import retrofit2.HttpException

object ErrorHandler {

    fun getApiErrorMessage(responce: Throwable): String {
        return try {
            val jsonObject =
                JSONObject(String((responce as HttpException).response().errorBody()?.bytes()!!))
            Log.e("getApiErrorMessage==>", "=======>"+responce.toString())
            jsonObject.getString("message")
        } catch (ex: Exception) {
            "Oops, something went wrong. Let\'s try it again."
        }
    }
}
