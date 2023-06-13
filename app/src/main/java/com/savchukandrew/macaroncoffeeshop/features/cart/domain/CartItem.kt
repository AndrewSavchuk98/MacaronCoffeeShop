package com.savchukandrew.macaroncoffeeshop.features.cart.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    val id: String,
    val image: String,
    val productName: String,
    val description: String,
    val price: Int,
    var quantity: Int
): Parcelable
