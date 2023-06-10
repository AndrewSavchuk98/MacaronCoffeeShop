package com.savchukandrew.macaroncoffeeshop.features.home.domain

import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>
}