package com.savchukandrew.macaroncoffeeshop.data.local

import com.savchukandrew.macaroncoffeeshop.data.local.datasource.RoomCartDataSource
import com.savchukandrew.macaroncoffeeshop.data.local.entities.RoomCartItemEntity
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRepositoryImp @Inject constructor(
    private val roomCartDataSource: RoomCartDataSource,
    private val dispatcher: CoroutineDispatcher
) : CartRepository {
    override suspend fun saveData(data: CartItem) = withContext(dispatcher) {
        roomCartDataSource.write(
            RoomCartItemEntity(
                productId = data.id,
                image = data.image,
                productName = data.productName,
                description = data.description,
                price = data.price,
                quantity = data.quantity
            )
        )

    }

    override suspend fun readData(): Flow<List<CartItem>> = withContext(dispatcher) {
        return@withContext roomCartDataSource.read().map { list ->
            list.map {
                CartItem(
                    id = it.productId,
                    image = it.image,
                    productName = it.productName,
                    description = it.description,
                    price = it.price,
                    quantity = it.quantity
                )
            }
        }
    }
}