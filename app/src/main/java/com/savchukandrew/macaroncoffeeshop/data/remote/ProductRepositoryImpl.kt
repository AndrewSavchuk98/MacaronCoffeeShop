package com.savchukandrew.macaroncoffeeshop.data.remote

import com.savchukandrew.macaroncoffeeshop.features.detail.domain.ProductDetailRepository
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.ProductDetail
import com.savchukandrew.macaroncoffeeshop.features.home.domain.ProductRepository
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: RemoteProductDataSource
) : ProductDetailRepository, ProductRepository {

    override suspend fun getProductByID(productId: String): ProductDetail {
        val productEntity = dataSource.getProductById(productId)

        return ProductDetail(
            name = productEntity.name,
            image = productEntity.image,
            description = productEntity.description,
            categoryId = productEntity.category
        )
    }

    override suspend fun getProducts(): List<Product> {
        return dataSource.getProducts().map {
            Product(
                id = it.id,
                title = it.name,
                imageUrl = it.image
            )
        }
    }
}