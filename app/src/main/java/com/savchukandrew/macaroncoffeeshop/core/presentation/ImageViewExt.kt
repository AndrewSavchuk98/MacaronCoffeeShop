package com.savchukandrew.macaroncoffeeshop.core.presentation

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation

fun ImageView.load(url: String) {
    load(url) {
        transformations(RoundedCornersTransformation(50f))
    }
}