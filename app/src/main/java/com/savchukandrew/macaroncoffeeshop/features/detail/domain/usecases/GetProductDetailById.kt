package com.savchukandrew.macaroncoffeeshop.features.detail.domain.usecases

import com.savchukandrew.macaroncoffeeshop.features.detail.domain.ProductDetailRepository
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.ProductDetail
import javax.inject.Inject

class GetProductDetailById @Inject constructor(private val repository: ProductDetailRepository) {

    suspend operator fun invoke(id: String): ProductDetail {
        return repository.getProductByID(id)
    }
}