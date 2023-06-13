package com.savchukandrew.macaroncoffeeshop.features.home.domain.usecases

import com.savchukandrew.macaroncoffeeshop.features.home.domain.ProductRepository
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities
import javax.inject.Inject

class GetDrinksUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<SectionEntities> {
        return listOf(SectionEntities(4, "Hot coffee", repository.getProducts().filter {
            it.category == "Hot coffee"
        }))
    }
}