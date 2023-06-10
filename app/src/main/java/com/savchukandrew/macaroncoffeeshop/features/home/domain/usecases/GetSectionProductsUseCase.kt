package com.savchukandrew.macaroncoffeeshop.features.home.domain.usecases

import com.savchukandrew.macaroncoffeeshop.features.home.domain.ProductRepository
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities
import javax.inject.Inject

class GetSectionProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): List<SectionEntities> {
        val featuresList = repository.getProducts().shuffled()
        val newestList = repository.getProducts().shuffled()
        val popularList = repository.getProducts().shuffled()

        return listOf(
            SectionEntities(1, "Features", featuresList),
            SectionEntities(2, "Newest", newestList),
            SectionEntities(3, "Popular", popularList)
        )
    }
}