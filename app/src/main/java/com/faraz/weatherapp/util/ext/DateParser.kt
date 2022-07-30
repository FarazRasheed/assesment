package com.faraz.weatherapp.util.ext

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun parseDate(date: String): String{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    var convertedDate: Date? = null
    var formattedDate: String? = null
    try {
        convertedDate = sdf.parse(date)
        formattedDate = convertedDate?.let { SimpleDateFormat("dd MMM").format(it) }
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate!!
}