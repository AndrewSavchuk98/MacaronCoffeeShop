package com.savchukandrew.macaroncoffeeshop.features.home.domain.models

data class Product(
    val id: String = "",
    val title: String,
    val imageUrl: String
) : java.io.Serializable