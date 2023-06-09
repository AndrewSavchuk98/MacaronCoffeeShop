package com.savchukandrew.macaroncoffeeshop.features.detail.domain

import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.ProductDetail

interface ProductDetailRepository {

    suspend fun getProductByID(productId: String): ProductDetail
}