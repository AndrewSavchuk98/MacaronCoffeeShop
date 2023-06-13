package com.savchukandrew.macaroncoffeeshop.features.offer.domain.models

data class OfferItem(
    val id: String,
    val productName: String,
    val description: String,
    val quantity: Int
)
