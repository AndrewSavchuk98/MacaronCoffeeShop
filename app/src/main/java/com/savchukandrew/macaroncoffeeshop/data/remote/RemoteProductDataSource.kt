package com.savchukandrew.macaroncoffeeshop.data.remote

import com.savchukandrew.macaroncoffeeshop.data.remote.entities.ProductRemoteEntity

interface RemoteProductDataSource {

    suspend fun getProducts(): List<ProductRemoteEntity>

    suspend fun getProductById(productId: String): ProductRemoteEntity
}