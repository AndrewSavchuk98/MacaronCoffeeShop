package com.savchukandrew.macaroncoffeeshop.features.cart.domain

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun saveData(data: CartItem)

    suspend fun deleteData(data: CartItem)

    suspend fun readData(): Flow<List<CartItem>>
}