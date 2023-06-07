package com.savchukandrew.macaroncoffeeshop.features.home.domain.models

data class SectionEntities(
    val id: Int,
    val title: String,
    val list: List<Product>
) : java.io.Serializable