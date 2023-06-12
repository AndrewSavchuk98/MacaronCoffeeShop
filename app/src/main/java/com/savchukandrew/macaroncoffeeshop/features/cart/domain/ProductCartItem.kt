package com.savchukandrew.macaroncoffeeshop.features.cart.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductCartItem(
    val id: String,
    val name: String,
    val description: String,
) : Parcelable
