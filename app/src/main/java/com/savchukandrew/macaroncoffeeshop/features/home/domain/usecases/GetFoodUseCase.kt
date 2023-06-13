package com.savchukandrew.macaroncoffeeshop.features.home.domain.usecases

import com.savchukandrew.macaroncoffeeshop.features.home.domain.ProductRepository
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): List<SectionEntities> {
        val bakeryList = repository.getProducts().filter {
            it.category == "Bakery"
        }
        val breakfastList = repository.getProducts().filter {
            it.category == "Hot breakfast"
        }

        return listOf(
            SectionEntities(5, "Bakery", bakeryList),
            SectionEntities(6, "Hot breakfast", breakfastList)
        )
    }


}