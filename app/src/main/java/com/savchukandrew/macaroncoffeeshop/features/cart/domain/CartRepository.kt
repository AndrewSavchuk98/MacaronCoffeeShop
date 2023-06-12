package com.savchukandrew.macaroncoffeeshop.features.cart.domain

interface CartRepository {

    suspend fun saveData(data: List<CartItem>)

    suspend fun readData(): List<CartItem>
}