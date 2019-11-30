package com.ewind.newsapi.util.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Janith on 24/01/18.
 */

fun ImageView.loadImageCenterCrop(url: String?) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions().centerCrop())
        .into(this)
}
