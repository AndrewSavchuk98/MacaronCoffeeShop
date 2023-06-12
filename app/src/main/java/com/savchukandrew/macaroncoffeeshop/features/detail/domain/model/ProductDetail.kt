package com.savchukandrew.macaroncoffeeshop.features.detail.domain.model

data class ProductDetail(
    val name: String,
    val image: String,
    val description: String,
    val categoryId: String,
    var price: Int,
    val size: String
)
